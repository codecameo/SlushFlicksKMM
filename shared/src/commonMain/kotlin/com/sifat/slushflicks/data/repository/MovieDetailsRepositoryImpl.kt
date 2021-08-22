package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImpl : MovieDetailsRepository {
    override fun getMovieDetails(movieId: Long): DataState<MovieEntity> {
        TODO("Not yet implemented")
    }

    override fun getMovieVideo(movieId: Long): DataState<String> {
        TODO("Not yet implemented")
    }

    override fun getMovieCast(movieId: Long): DataState<CastColumn> {
        TODO("Not yet implemented")
    }

    override fun getSimilarMovies(movieId: Long): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getRecommendationMovies(movieId: Long): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getReviews(movieId: Long): DataState<List<ReviewApiModel>> {
        TODO("Not yet implemented")
    }

    override fun updateRecentMovie(movieId: Long) {
        TODO("Not yet implemented")
    }
}
