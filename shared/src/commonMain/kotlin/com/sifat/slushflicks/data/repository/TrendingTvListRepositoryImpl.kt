package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.mapper.toMovieEntity
import com.sifat.slushflicks.data.mapper.toTvShowCollectionEntity
import com.sifat.slushflicks.data.remote.api.TvShowApi
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.TvListRepository

class TrendingTvListRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvListRepository {
    override suspend fun getTvList(collection: String, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(tvShowApi.getTrendingTvShow(page)) {
                it?.results?.map { it.toEntity(localDataManager.getGenres()) } ?: emptyList()
            }.also {
                (it as? DataState.Success)?.data?.let { data ->
                    localDataManager.softInsertMovie(data.map { it.toMovieEntity() })
                    localDataManager.addTvCollection(
                        data.mapIndexed { index: Int, showEntity: ShowEntity ->
                            showEntity.toTvShowCollectionEntity(collection, page, index)
                        }
                    )
                }
            }
        }
    }
}
