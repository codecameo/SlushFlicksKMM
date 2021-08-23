package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.mapper.toMovieCollectionEntity
import com.sifat.slushflicks.data.mapper.toMovieEntity
import com.sifat.slushflicks.data.remote.api.MovieApi
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.MovieListRepository

class TrendingMovieListRepositoryImpl(
    private val movieApi: MovieApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieListRepository {
    override suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(movieApi.getTrendingMovies(page)) {
                it?.results?.map { it.toEntity(localDataManager.getGenres()) } ?: emptyList()
            }.also {
                (it as? DataState.Success)?.data?.let { data ->
                    localDataManager.softInsertMovie(data.map { it.toMovieEntity() })
                    localDataManager.addMovieCollection(
                        data.mapIndexed { index: Int, showEntity: ShowEntity ->
                            showEntity.toMovieCollectionEntity(collection, page, index)
                        }
                    )
                }
            }
        }
    }
}
