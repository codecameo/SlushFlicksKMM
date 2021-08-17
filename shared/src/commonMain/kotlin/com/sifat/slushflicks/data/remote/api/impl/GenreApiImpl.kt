package com.sifat.slushflicks.data.remote.api.impl

import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.GENRES_MOVIE_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.GENRES_TV_URL
import com.sifat.slushflicks.data.remote.ApiErrorParser
import com.sifat.slushflicks.data.remote.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.api.GenreApi
import com.sifat.slushflicks.data.remote.model.GenreListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder

class GenreApiImpl(
    private val client: HttpClient,
    private val baseUrlBuilder: URLBuilder,
    private val apiKey: String,
    apiErrorParser: ApiErrorParser
) : BaseApiImpl(apiErrorParser), GenreApi {

    override suspend fun getTvGenre(): ApiResponse<GenreListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                encodedPath = GENRES_TV_URL
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getMovieGenre(): ApiResponse<GenreListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                encodedPath = GENRES_MOVIE_URL
                parameters.append(QUERY_KEY_API_KEY, apiKey)
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }
}
