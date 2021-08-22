package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Long): DataState<MovieEntity>
    fun getMovieVideo(movieId: Long): DataState<String>
    fun getMovieCast(movieId: Long): DataState<CastColumn>
    fun getSimilarMovies(movieId: Long): DataState<List<ShowEntity>>
    fun getRecommendationMovies(movieId: Long): DataState<List<ShowEntity>>
    fun getReviews(movieId: Long): DataState<List<ReviewApiModel>>
    fun updateRecentMovie(movieId: Long)
}
