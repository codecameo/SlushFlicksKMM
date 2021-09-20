package com.sifat.common.data.remote.api

import com.sifat.common.data.remote.ApiResponse
import com.sifat.common.data.remote.model.CreditsApiModel
import com.sifat.common.data.remote.model.MovieDetailsApiModel
import com.sifat.common.data.remote.model.MovieListApiModel
import com.sifat.common.data.remote.model.ReviewListApiModel
import com.sifat.common.data.remote.model.VideoListApiModel

interface MovieApi {
    suspend fun getTrendingMovies(page: Int): ApiResponse<MovieListApiModel>

    suspend fun getMoviesList(collection: String, page: Int): ApiResponse<MovieListApiModel>

    suspend fun getMovieDetails(movieId: Long): ApiResponse<MovieDetailsApiModel>

    suspend fun getMovieVideos(movieId: Long): ApiResponse<VideoListApiModel>

    suspend fun getMovieCredits(movieId: Long): ApiResponse<CreditsApiModel>

    suspend fun getMovieReviews(movieId: Long, page: Int): ApiResponse<ReviewListApiModel>

    suspend fun getRelatedMovies(movieId: Long, relation: String, page: Int):
        ApiResponse<MovieListApiModel>
}
