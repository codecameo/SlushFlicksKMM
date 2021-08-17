package com.sifat.slushflicks.data.remote.api.impl

import com.sifat.slushflicks.data.remote.ApiEndPoint
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.MOVIE_COLLECTION_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.MOVIE_CREDITS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.MOVIE_DETAILS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.MOVIE_REVIEWS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.MOVIE_VIDEOS_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.sifat.slushflicks.data.remote.ApiErrorParser
import com.sifat.slushflicks.data.remote.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.data.remote.ApiRequest.Companion.QUERY_KEY_PAGE
import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.api.MovieApi
import com.sifat.slushflicks.data.remote.model.CreditsApiModel
import com.sifat.slushflicks.data.remote.model.MovieDetailsApiModel
import com.sifat.slushflicks.data.remote.model.MovieListApiModel
import com.sifat.slushflicks.data.remote.model.ReviewListApiModel
import com.sifat.slushflicks.data.remote.model.VideoListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder

class MovieApiImpl(
    private val client: HttpClient,
    private val baseUrlBuilder: URLBuilder,
    private val apiKey: String,
    apiErrorParser: ApiErrorParser
) : BaseApiImpl(apiErrorParser), MovieApi {

    override suspend fun getTrendingMovies(page: Int): ApiResponse<MovieListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                encodedPath = TRENDING_MOVIE_URL
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getMoviesList(
        collection: String,
        page: Int
    ): ApiResponse<MovieListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(MOVIE_COLLECTION_URL, collection))
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getMovieDetails(movieId: Long): ApiResponse<MovieDetailsApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(MOVIE_DETAILS_URL, movieId.toString()))
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getMovieVideos(movieId: Long): ApiResponse<VideoListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(MOVIE_VIDEOS_URL, movieId.toString()))
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getMovieCredits(movieId: Long): ApiResponse<CreditsApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(MOVIE_CREDITS_URL, movieId.toString()))
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getMovieReviews(
        movieId: Long,
        page: Int
    ): ApiResponse<ReviewListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(MOVIE_REVIEWS_URL, movieId.toString()))
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getRelatedMovies(
        movieId: Long,
        relation: String,
        page: Int
    ): ApiResponse<MovieListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                path(listOf(ApiEndPoint.RELATED_MOVIE_URL, movieId.toString(), relation))
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }
}
