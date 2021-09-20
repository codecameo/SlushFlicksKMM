package com.sifat.common.data.repository

import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.mapper.toEntity
import com.sifat.common.data.mapper.toMovieCollectionEntity
import com.sifat.common.data.mapper.toMovieEntity
import com.sifat.common.data.remote.api.MovieApi
import com.sifat.common.data.state.DataState
import com.sifat.common.data.state.DataState.Success
import com.sifat.common.domain.repository.MovieListRepository

class TrendingMovieListRepositoryImpl(
    private val movieApi: MovieApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieListRepository {
    override suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowEntity>> {
        return execute(fallback = {
            Success(data = localDataManager.getPagingMovies(collection, page))
        }) {
            getDataState(movieApi.getTrendingMovies(page)) {
                it?.results?.map { it.toEntity(localDataManager.getGenres()) } ?: emptyList()
            }.also {
                (it as? Success)?.data?.let { data ->
                    localDataManager.softInsertMovie(data.map { it.toMovieEntity() })
                    val movieCollections = data.mapIndexed { index: Int, showEntity: ShowEntity ->
                        showEntity.toMovieCollectionEntity(collection, page, index)
                    }
                    if (page == 1) {
                        localDataManager.insertNewMovieCollection(collection, movieCollections)
                    } else {
                        localDataManager.addMovieCollection(movieCollections)
                    }
                }
            }
        }
    }
}
