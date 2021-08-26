package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

interface RecentRepository {
    suspend fun getRecentMovieList(page: Int): DataState<List<ShowEntity>>
    suspend fun getRecentTvShowList(page: Int): DataState<List<ShowEntity>>
    suspend fun updateRecentMovie(movieId: Long)
    suspend fun updateRecentTvShow(tvShowId: Long)
}
