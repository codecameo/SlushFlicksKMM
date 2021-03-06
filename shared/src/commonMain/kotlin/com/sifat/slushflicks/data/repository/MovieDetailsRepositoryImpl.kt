package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Label.RECOMMENDATION_LABEL
import com.sifat.slushflicks.data.Label.SIMILAR_LABEL
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.isYoutubeModel
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.mapper.toColumn
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.remote.api.MovieApi
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Error
import com.sifat.slushflicks.data.state.DataState.Success
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRepositoryImpl(
    private val movieApi: MovieApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Long): Flow<DataState<MovieEntity>> {
        return flow {
            localDataManager.getMovieDetails(movieId).also {
                emit(Success(data = it))
            }
            updateMovieDetails(movieId).let { state ->
                when (state) {
                    is Success -> localDataManager.getMovieDetails(movieId).also {
                        emit(Success(data = it, message = state.message))
                    }
                    is Error -> localDataManager.getMovieDetails(movieId).also {
                        emit(
                            Error(
                                data = it,
                                errorMessage = state.errorMessage,
                                statusCode = state.statusCode
                            )
                        )
                    }
                }
            }
        }
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
                movieApi.getRelatedMovies(movieId = movieId, relation = SIMILAR_LABEL, page = page)
            ) {
                val genres = localDataManager.getGenres()
                it?.results?.map { it.toEntity(genres) } ?: emptyList()
            }
        }
    }

    override suspend fun getRecommendMovies(movieId: Long, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(
                movieApi.getRelatedMovies(
                    movieId = movieId, relation = RECOMMENDATION_LABEL, page = page
                )
            ) {
                val genres = localDataManager.getGenres()
                it?.results?.map { it.toEntity(genres) } ?: emptyList()
            }
        }
    }

    override suspend fun getReviews(movieId: Long, page: Int): DataState<List<ReviewApiModel>> {
        return execute {
            getDataState(movieApi.getMovieReviews(movieId, page)) {
                it?.results ?: emptyList()
            }
        }
    }

    private suspend fun updateMovieDetails(movieId: Long): DataState<MovieEntity> {
        return execute {
            getDataState(movieApi.getMovieDetails(movieId)) {
                it?.toEntity()
            }.also {
                (it as? Success)?.data?.let { movie ->
                    localDataManager.updateMovieDetails(movie)
                }
            }
        }
    }
}
