package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.CreditsApiModel
import com.sifat.slushflicks.data.remote.model.MovieDetailsApiModel
import com.sifat.slushflicks.data.remote.model.MovieListApiModel
import com.sifat.slushflicks.data.remote.model.ReviewListApiModel
import com.sifat.slushflicks.data.remote.model.VideoListApiModel

interface MovieApi {
    fun getMoviesList(collection: String, apiKey: String, page: Int): ApiResponse<MovieListApiModel>

    fun getTrendingMovies(apiKey: String, page: Int): ApiResponse<MovieListApiModel>

    fun getMovieDetails(movieId: Long, apiKey: String): ApiResponse<MovieDetailsApiModel>

    fun getMovieVideos(movieId: Long, apiKey: String): ApiResponse<VideoListApiModel>

    fun getMovieCredits(movieId: Long, apiKey: String): ApiResponse<CreditsApiModel>

    fun getMovieReviews(movieId: Long, apiKey: String, page: Int): ApiResponse<ReviewListApiModel>

    fun getRelatedMovies(movieId: Long, relation: String, apiKey: String, page: Int):
        ApiResponse<MovieListApiModel>
}
