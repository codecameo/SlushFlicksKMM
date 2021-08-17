package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.CreditsApiModel
import com.sifat.slushflicks.data.remote.model.ReviewListApiModel
import com.sifat.slushflicks.data.remote.model.TvListApiModel
import com.sifat.slushflicks.data.remote.model.TvShowDetailsApiModel
import com.sifat.slushflicks.data.remote.model.VideoListApiModel

interface TvShowApi {
    fun getTrendingTvShow(apiKey: String, page: Int): ApiResponse<TvListApiModel>

    fun getTvShowList(collection: String, apiKey: String, page: Int): ApiResponse<TvListApiModel>

    fun getTvShowDetails(tvShowId: Long, apiKey: String): ApiResponse<TvShowDetailsApiModel>

    fun getTvShowCredits(tvShowId: Long, apiKey: String): ApiResponse<CreditsApiModel>

    fun getTvShowVideos(tvShowId: Long, season: Int, apiKey: String): ApiResponse<VideoListApiModel>

    fun getTvShowReviews(tvShowId: Long, apiKey: String, page: Int): ApiResponse<ReviewListApiModel>

    fun getRelatedTvShows(
        tvShowId: Long,
        relation: String,
        apiKey: String,
        page: Int
    ): ApiResponse<TvListApiModel>
}
