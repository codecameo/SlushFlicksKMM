package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long): DataState<MovieEntity>
    suspend fun getMovieVideo(movieId: Long): DataState<String>
    suspend fun getMovieCast(movieId: Long): DataState<List<CastColumn>>
    suspend fun getSimilarMovies(movieId: Long, page: Int = 1): DataState<List<ShowEntity>>
    suspend fun getRecommendMovies(movieId: Long, page: Int = 1): DataState<List<ShowEntity>>
    suspend fun getReviews(movieId: Long, page: Int): DataState<List<ReviewApiModel>>
}
