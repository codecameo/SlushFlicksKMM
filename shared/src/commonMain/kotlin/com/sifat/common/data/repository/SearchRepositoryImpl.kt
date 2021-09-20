package com.sifat.common.data.repository

import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.mapper.toEntity
import com.sifat.common.data.model.SearchResult
import com.sifat.common.data.remote.api.SearchApi
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.repository.SearchRepository

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
