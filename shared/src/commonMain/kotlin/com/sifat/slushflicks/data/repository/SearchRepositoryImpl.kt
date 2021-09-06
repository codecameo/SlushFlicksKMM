package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.model.SearchResult
import com.sifat.slushflicks.data.remote.api.SearchApi
import com.sifat.slushflicks.data.state.DataState
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
                    result = it?.results?.map { it.toEntity(localDataManager.getGenres()) }
                        ?: emptyList()
                )
            }
        }
    }

    override suspend fun searchTvShows(
        query: String,
        page: Int
    ): DataState<SearchResult<ShowEntity>> {
        return execute {
            getDataState(searchApi.getSearchTvShows(query, page)) {
                SearchResult(
                    query = query,
                    page = page,
                    result = it?.results?.map { it.toEntity(localDataManager.getGenres()) }
                        ?: emptyList()
                )
            }
        }
    }
}
