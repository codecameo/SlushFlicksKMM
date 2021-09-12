package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface TvDetailsRepository {
    suspend fun getTvShowDetails(tvShowId: Long): Flow<DataState<TvShowEntity>>
    suspend fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): DataState<String>
    suspend fun getTvShowCast(tvShowId: Long): DataState<List<CastColumn>>
    suspend fun getSimilarTvShows(tvShowId: Long, page: Int): DataState<List<ShowEntity>>
    suspend fun getRecommendationTvShows(tvShowId: Long, page: Int): DataState<List<ShowEntity>>
    suspend fun getTvShowReviews(tvShowId: Long, page: Int): DataState<List<ReviewApiModel>>
}
