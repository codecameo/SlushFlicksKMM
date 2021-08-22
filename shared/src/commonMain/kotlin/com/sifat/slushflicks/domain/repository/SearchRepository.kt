package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

interface SearchRepository {
    fun searchMovies(query: String, page: Int): DataState<List<ShowEntity>>

    fun searchTvShows(query: String, page: Int): DataState<List<ShowEntity>>

    fun getRecentMovieList(page: Int): DataState<List<ShowEntity>>

    fun getRecentTvShowList(page: Int): DataState<List<ShowEntity>>
}
