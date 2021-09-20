package com.sifat.common.domain.repository

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.common.data.cache.column.CastColumn
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.remote.model.ReviewApiModel
import com.sifat.common.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long): Flow<DataState<MovieEntity>>
    suspend fun getMovieVideo(movieId: Long): DataState<String>
    suspend fun getMovieCast(movieId: Long): DataState<List<CastColumn>>
    suspend fun getSimilarMovies(movieId: Long, page: Int = 1): DataState<List<ShowEntity>>
    suspend fun getRecommendMovies(movieId: Long, page: Int = 1): DataState<List<ShowEntity>>
    suspend fun getReviews(movieId: Long, page: Int): DataState<List<ReviewApiModel>>
}
