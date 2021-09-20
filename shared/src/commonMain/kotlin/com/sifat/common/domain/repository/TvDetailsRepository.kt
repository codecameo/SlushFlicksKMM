package com.sifat.common.domain.repository

import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.common.data.cache.column.CastColumn
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.remote.model.ReviewApiModel
import com.sifat.common.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface TvDetailsRepository {
    suspend fun getTvShowDetails(tvShowId: Long): Flow<DataState<TvShowEntity>>
    suspend fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): DataState<String>
    suspend fun getTvShowCast(tvShowId: Long): DataState<List<CastColumn>>
    suspend fun getSimilarTvShows(tvShowId: Long, page: Int): DataState<List<ShowEntity>>
    suspend fun getRecommendationTvShows(tvShowId: Long, page: Int): DataState<List<ShowEntity>>
    suspend fun getTvShowReviews(tvShowId: Long, page: Int): DataState<List<ReviewApiModel>>
}
