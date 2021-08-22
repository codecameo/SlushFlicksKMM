package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState

interface TvDetailsRepository {
    fun getTvShowDetails(tvShowId: Long): DataState<TvShowEntity>
    fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): DataState<String>
    fun getTvShowCast(tvShowId: Long): DataState<Int>
    fun getSimilarTvShows(tvShowId: Long): DataState<List<ShowEntity>>
    fun getRecommendationTvShows(tvShowId: Long): DataState<List<ShowEntity>>
    fun getTvShowReviews(tvShowId: Long): DataState<List<ReviewApiModel>>
    fun updateRecentTvShow(tvShowId: Long)
}
