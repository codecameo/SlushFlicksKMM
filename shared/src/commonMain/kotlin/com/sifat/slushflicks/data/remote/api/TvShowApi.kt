package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.CreditsApiModel
import com.sifat.slushflicks.data.remote.model.ReviewListApiModel
import com.sifat.slushflicks.data.remote.model.TvListApiModel
import com.sifat.slushflicks.data.remote.model.TvShowDetailsApiModel
import com.sifat.slushflicks.data.remote.model.VideoListApiModel

interface TvShowApi {
    suspend fun getTrendingTvShow(page: Int): ApiResponse<TvListApiModel>

    suspend fun getTvShowList(collection: String, page: Int): ApiResponse<TvListApiModel>

    suspend fun getTvShowDetails(tvShowId: Long): ApiResponse<TvShowDetailsApiModel>

    suspend fun getTvShowCredits(tvShowId: Long): ApiResponse<CreditsApiModel>

    suspend fun getTvShowVideos(tvShowId: Long, season: Int): ApiResponse<VideoListApiModel>

    suspend fun getTvShowReviews(tvShowId: Long, page: Int): ApiResponse<ReviewListApiModel>

    suspend fun getRelatedTvShows(
        tvShowId: Long,
        relation: String,
        page: Int
    ): ApiResponse<TvListApiModel>
}
