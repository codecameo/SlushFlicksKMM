package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.DEFAULT_LONG
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Label.Companion.RECENTLY_VISITED_MOVIE
import com.sifat.slushflicks.data.Label.Companion.RECOMMENDATION_LABEL
import com.sifat.slushflicks.data.Label.Companion.SIMILAR_LABEL
import com.sifat.slushflicks.data.cache.MovieCollectionEntity
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.isYoutubeModel
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.manager.TimeManager
import com.sifat.slushflicks.data.mapper.toColumn
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.remote.api.MovieApi
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Success
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImpl(
    private val movieApi: MovieApi,
    private val localDataManager: LocalDataManager,
    private val timeManager: TimeManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Long): DataState<MovieEntity> {
        return localDataManager.getMovieDetails(movieId)?.let { entity ->
            return if (hasOtherData(entity)) Success(data = entity) else fetchMovieDetails(movieId)
        } ?: fetchMovieDetails(movieId)
    }

    override suspend fun getMovieVideo(movieId: Long): DataState<String> {
        return execute {
            getDataState(movieApi.getMovieVideos(movieId)) { apiModel ->
                apiModel?.results?.find { isYoutubeModel(it) }?.key ?: EMPTY_STRING
            }.also {
                (it as? Success)?.data?.let { video ->
                    localDataManager.updateMovieDetails(videoKey = video, movieId = movieId)
                }
            }
        }
    }

    override suspend fun getMovieCast(movieId: Long): DataState<List<CastColumn>> {
        return execute {
            getDataState(movieApi.getMovieCredits(movieId)) { credits ->
                credits?.casts?.map { it.toColumn() }
            }.also {
                (it as? Success)?.data?.let { casts ->
                    localDataManager.updateMovieDetails(casts = casts, movieId = movieId)
                }
            }
        }
    }

    override suspend fun getSimilarMovies(movieId: Long, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(
                movieApi.getRelatedMovies(
                    movieId = movieId,
                    relation = SIMILAR_LABEL,
                    page = page
                )
            )
        }
    }

    override suspend fun getRecommendMovies(movieId: Long, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(movieApi.getRelatedMovies(movieId, RECOMMENDATION_LABEL, page))
        }
    }

    override suspend fun getReviews(movieId: Long, page: Int): DataState<List<ReviewApiModel>> {
        return execute {
            getDataState(movieApi.getMovieReviews(movieId, page))
        }
    }

    override suspend fun updateRecentMovie(movieId: Long) {
        val time = (timeManager.getCurrentTime() / 1000).toInt()
        localDataManager.insertNewMovieCollection(
            MovieCollectionEntity(
                collection = RECENTLY_VISITED_MOVIE,
                id = movieId,
                position = -1 * time // Reversing the order
            )
        )
    }

    private suspend fun fetchMovieDetails(movieId: Long): DataState<MovieEntity> {
        return execute {
            getDataState(movieApi.getMovieDetails(movieId)) {
                it?.toEntity()
            }.also {
                (it as? Success)?.data?.let { movie ->
                    localDataManager.insertMovieDetails(movie)
                }
            }
        }
    }

    private fun hasOtherData(entity: MovieEntity): Boolean {
        return entity.run {
            runtime != DEFAULT_INT ||
                voteCount != DEFAULT_INT ||
                tagline != EMPTY_STRING ||
                status != EMPTY_STRING ||
                posterPath != EMPTY_STRING ||
                popularity != DEFAULT_DOUBLE ||
                budget != DEFAULT_LONG ||
                revenue != DEFAULT_LONG
        }
    }
}
