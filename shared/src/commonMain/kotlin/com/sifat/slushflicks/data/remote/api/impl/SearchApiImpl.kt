package com.sifat.slushflicks.data.remote.api.impl

import com.sifat.slushflicks.data.remote.ApiEndPoint.SEARCH_MOVIE_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.SEARCH_TV_SHOW_URL
import com.sifat.slushflicks.data.remote.ApiErrorParser
import com.sifat.slushflicks.data.remote.ApiRequest.QUERY_KEY_API_KEY
import com.sifat.slushflicks.data.remote.ApiRequest.QUERY_KEY_PAGE
import com.sifat.slushflicks.data.remote.ApiRequest.QUERY_KEY_SEARCH
import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.api.SearchApi
import com.sifat.slushflicks.data.remote.model.MovieListApiModel
import com.sifat.slushflicks.data.remote.model.TvListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder

class SearchApiImpl(
    private val client: HttpClient,
    private val baseUrlBuilder: URLBuilder,
    private val apiKey: String,
    apiErrorParser: ApiErrorParser
) : BaseApiImpl(apiErrorParser), SearchApi {

    override suspend fun getSearchMovies(query: String, page: Int): ApiResponse<MovieListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                encodedPath = SEARCH_MOVIE_URL
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                    append(QUERY_KEY_SEARCH, query)
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }

    override suspend fun getSearchTvShows(query: String, page: Int): ApiResponse<TvListApiModel> {
        return execute {
            val urlBuilder = URLBuilder(baseUrlBuilder).apply {
                encodedPath = SEARCH_TV_SHOW_URL
                parameters.run {
                    append(QUERY_KEY_API_KEY, apiKey)
                    append(QUERY_KEY_PAGE, page.toString())
                    append(QUERY_KEY_SEARCH, query)
                }
            }
            client.get(urlString = urlBuilder.buildString())
        }
    }
}
