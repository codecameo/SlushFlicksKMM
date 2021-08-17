package com.sifat.slushflicks.data.remote.api.impl

import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.RELATED_TV_SHOW_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TRENDING_TV_SHOW_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_CREDITS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_SHOW_COLLECTION_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_SHOW_DETAILS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_SHOW_REVIEWS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_VIDEO_URL
import com.sifat.slushflicks.data.remote.ApiErrorParser
import com.sifat.slushflicks.data.remote.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.data.remote.ApiRequest.Companion.QUERY_KEY_PAGE
import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.api.TvShowApi
import com.sifat.slushflicks.data.remote.model.CreditsApiModel
import com.sifat.slushflicks.data.remote.model.ReviewListApiModel
import com.sifat.slushflicks.data.remote.model.TvListApiModel
import com.sifat.slushflicks.data.remote.model.TvShowDetailsApiModel
import com.sifat.slushflicks.data.remote.model.VideoListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder

class TvShowApiImpl(
    private val client: HttpClient,
    private val baseUrlBuilder: URLBuilder,
    private val apiKey: String,
    apiErrorParser: ApiErrorParser
) : BaseApiImpl(apiErrorParser), TvShowApi {
    override suspend fun getTrendingTvShow(page: Int): ApiResponse<TvListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                encodedPath = TRENDING_TV_SHOW_URL
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getTvShowList(
        collection: String,
        page: Int
    ): ApiResponse<TvListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(TV_SHOW_COLLECTION_URL, collection))
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getTvShowDetails(
        tvShowId: Long
    ): ApiResponse<TvShowDetailsApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(TV_SHOW_DETAILS_URL, tvShowId.toString()))
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getTvShowCredits(
        tvShowId: Long
    ): ApiResponse<CreditsApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(TV_CREDITS_URL, tvShowId.toString()))
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getTvShowVideos(
        tvShowId: Long,
        season: Int
    ): ApiResponse<VideoListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(TV_VIDEO_URL, tvShowId.toString(), season.toString()))
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getTvShowReviews(
        tvShowId: Long,
        page: Int
    ): ApiResponse<ReviewListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(TV_SHOW_REVIEWS_URL, tvShowId.toString()))
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getRelatedTvShows(
        tvShowId: Long,
        relation: String,
        page: Int
    ): ApiResponse<TvListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(RELATED_TV_SHOW_URL, tvShowId.toString(), relation))
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }
}
