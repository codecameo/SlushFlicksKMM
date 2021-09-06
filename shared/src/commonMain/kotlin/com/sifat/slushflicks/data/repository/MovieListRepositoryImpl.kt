package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.mapper.toMovieCollectionEntity
import com.sifat.slushflicks.data.mapper.toMovieEntity
import com.sifat.slushflicks.data.remote.api.MovieApi
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Success
import com.sifat.slushflicks.domain.repository.MovieListRepository

class MovieListRepositoryImpl(
    private val movieApi: MovieApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieListRepository {
    override suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowEntity>> {
        return execute(fallback = {
            Success(data = localDataManager.getPagingMovies(collection, page))
        }) {
            getDataState(movieApi.getMoviesList(collection, page)) {
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
