package com.sifat.common.domain.repository

import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.model.SearchResult
import com.sifat.common.data.state.DataState

interface SearchRepository {
    suspend fun searchMovies(query: String, page: Int): DataState<SearchResult<ShowEntity>>

    suspend fun searchTvShows(query: String, page: Int): DataState<SearchResult<ShowEntity>>
}
