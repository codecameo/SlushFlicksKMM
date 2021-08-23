package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.Label.Companion.RECENTLY_VISITED_MOVIE
import com.sifat.slushflicks.data.Label.Companion.RECENTLY_VISITED_TV_SHOW
import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.model.SearchResult
import com.sifat.slushflicks.data.remote.api.SearchApi
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Success
import com.sifat.slushflicks.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val searchApi: SearchApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), SearchRepository {
    override suspend fun searchMovies(
        query: String,
        page: Int
    ): DataState<SearchResult<ShowEntity>> {
        return execute {
            getDataState(searchApi.getSearchMovies(query, page)) {
                SearchResult(
                    query = query,
                    page = page,
                    result = it?.results?.map { it.toEntity() } ?: emptyList()
                )
            }
        }
    }

    override suspend fun searchTvShows(
        query: String,
        page: Int
    ): DataState<SearchResult<ShowEntity>> {
        return execute {
            getDataState(searchApi.getSearchMovies(query, page)) {
                SearchResult(
                    query = query,
                    page = page,
                    result = it?.results?.map { it.toEntity() } ?: emptyList()
                )
            }
        }
    }

    override suspend fun getRecentMovieList(page: Int): DataState<List<ShowEntity>> {
        return Success(localDataManager.getPagingMovies(RECENTLY_VISITED_MOVIE, page))
    }

    override suspend fun getRecentTvShowList(page: Int): DataState<List<ShowEntity>> {
        return Success(localDataManager.getPagingMovies(RECENTLY_VISITED_TV_SHOW, page))
    }
}
