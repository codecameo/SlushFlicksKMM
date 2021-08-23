package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.model.SearchResult
import com.sifat.slushflicks.data.state.DataState

interface SearchRepository {
    suspend fun searchMovies(query: String, page: Int): DataState<SearchResult<ShowEntity>>

    suspend fun searchTvShows(query: String, page: Int): DataState<SearchResult<ShowEntity>>

    suspend fun getRecentMovieList(page: Int): DataState<List<ShowEntity>>

    suspend fun getRecentTvShowList(page: Int): DataState<List<ShowEntity>>
}
