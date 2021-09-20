package com.sifat.common.data.repository

import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.mapper.toEntity
import com.sifat.common.data.mapper.toTvShowCollectionEntity
import com.sifat.common.data.mapper.toTvShowEntity
import com.sifat.common.data.remote.api.TvShowApi
import com.sifat.common.data.state.DataState
import com.sifat.common.data.state.DataState.Success
import com.sifat.common.domain.repository.TvListRepository

class TvListRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvListRepository {
    override suspend fun getTvList(collection: String, page: Int): DataState<List<ShowEntity>> {
        return execute(fallback = {
            Success(data = localDataManager.getPagingTvShows(collection, page))
        }) {
            getDataState(tvShowApi.getTvShowList(collection, page)) {
                it?.results?.map { it.toEntity(localDataManager.getGenres()) } ?: emptyList()
            }.also {
                (it as? Success)?.data?.let { data ->
                    localDataManager.softInsertTv(data.map { it.toTvShowEntity() })
                    val tvShowCollection = data.mapIndexed { index: Int, showEntity: ShowEntity ->
                        showEntity.toTvShowCollectionEntity(collection, page, index)
                    }
                    if (page == 1) {
                        localDataManager.insertNewTvCollection(collection, tvShowCollection)
                    } else {
                        localDataManager.addTvCollection(tvShowCollection)
                    }
                }
            }
        }
    }
}
