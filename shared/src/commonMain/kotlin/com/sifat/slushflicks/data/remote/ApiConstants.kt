package com.sifat.slushflicks.data.remote

import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.CREDITS_PATH
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.GENRES_MOVIE_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.GENRES_TV_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.MOVIE_PATH
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.REVIEWS_PATH
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.SEARCH_MOVIE_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.SEARCH_TV_SHOW_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TRENDING_TV_SHOW_URL
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_SESSION_PATH
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.TV_SHOW_PATH
import com.sifat.slushflicks.data.remote.ApiEndPoint.Companion.VIDEOS_PATH
import com.sifat.slushflicks.data.remote.MethodName.Companion.GET
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class ImageDimension(val dimension: String) {
    // "w92", "w154", "w185", "w342", "w500", "w780", or "original"
    W92("w92/"),
    W154("w154/"),
    W185("w185/"),
    W342("w342"),
    W500("w500/"),
    W780("w780/"),
    Original("original/")
}

class ApiRequest {
    companion object {
        const val QUERY_KEY_API_KEY = "api_key"
        const val QUERY_KEY_SEARCH = "query"
        const val QUERY_KEY_PAGE = "page"
        const val PATH_COLLECTION = "collection"
        const val PATH_MOVIE_ID = "movie_id"
        const val PATH_TV_SHOW_ID = "tv_show_id"
        const val PATH_TV_SEASON_NUMBER = "season_number"
        const val PATH_RELATION_TYPE = "relation_type"
    }
}

class ApiEndPoint {
    companion object {
        const val TRENDING_MOVIE_URL = "trending/movie/day"
        const val MOVIE_PATH = "movie"
        const val GENRES_MOVIE_URL = "genre/movie/list"
        const val SEARCH_MOVIE_URL = "search/movie"

        const val GENRES_TV_URL = "genre/tv/list"
        const val TRENDING_TV_SHOW_URL = "trending/tv/day"
        const val TV_SHOW_PATH = "tv"
        const val TV_SESSION_PATH = "season"
        const val SEARCH_TV_SHOW_URL = "search/tv"

        const val VIDEOS_PATH = "videos"
        const val CREDITS_PATH = "credits"
        const val REVIEWS_PATH = "reviews"
    }
}

class MethodName {
    companion object {
        const val GET = 1
    }
}

class ApiTag {
    companion object {
        const val TRENDING_MOVIE_API_TAG = TRENDING_MOVIE_URL + GET
        const val TV_GENRE_API_TAG = GENRES_TV_URL + GET
        const val MOVIE_GENRE_API_TAG = GENRES_MOVIE_URL + GET
        const val MOVIE_DETAIL_API_TAG = MOVIE_PATH + GET
        const val MOVIE_VIDEO_API_TAG = VIDEOS_PATH + GET
        const val MOVIE_CREDITS_API_TAG = CREDITS_PATH + GET
        const val MOVIE_REVIEWS_API_TAG = REVIEWS_PATH + GET
        const val MOVIE_SEARCH_API_TAG = SEARCH_MOVIE_URL + GET

        const val TRENDING_TV_API_TAG = TRENDING_TV_SHOW_URL + GET
        const val TV_SHOW_API_TAG = TV_SHOW_PATH + GET
        const val TV_VIDEO_API_TAG = TV_SESSION_PATH + GET
        const val TV_SHOW_SEARCH_API_TAG = SEARCH_TV_SHOW_URL + GET
    }
}

class StatusCode {
    companion object {
        const val NO_INTERNET_ERROR = 104
        const val SUCCESS = 200
        const val EMPTY_RESPONSE = 204
        const val RESOURCE_NOT_FOUND = 404
        const val UNAUTHORIZED = 401
        const val INTERNAL_ERROR = 105
        const val REQUEST_CANCELLED = 100
    }
}

@Serializable
data class ErrorResponse(
    @SerialName("status_code")
    val statusCode: Int? = null,
    @SerialName("status_message")
    val message: String? = null
)

const val TIMEOUT = 20 * 1000L
const val CONTENT_TYPE = "Content-Type"
const val APPLICATION_JSON = "application/json"
const val BASE_URL = "api.themoviedb.org/3"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
const val API_KEY = "5bd74c7a7bbfad5678fe1acd33fe732a"
