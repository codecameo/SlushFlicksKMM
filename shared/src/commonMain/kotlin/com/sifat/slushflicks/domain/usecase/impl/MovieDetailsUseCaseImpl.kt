package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Error
import com.sifat.slushflicks.data.state.DataState.Success
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.CastModel
import com.sifat.slushflicks.domain.model.MovieModel
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import com.sifat.slushflicks.domain.usecase.MovieDetailsUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MovieDetailsUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), MovieDetailsUseCase {
    override suspend fun execute(movieId: Long): DataState<MovieModel> {
        return movieDetailsRepository.getMovieDetails(movieId).let { state ->
            when (state) {
                is Error -> getErrorResponse(state) {
                    it?.toModel()
                }
                is Success -> Success(
                    data = getMovieDetails(state.data),
                    message = state.message
                )
            }
        }
    }

    private suspend fun getMovieDetails(movieEntity: MovieEntity?): MovieModel? {
        return movieEntity?.let {
            coroutineScope {
                val videoKeyJob = getVideoKeyAsync(movieEntity)
                val castJob = getCastsAsync(movieEntity)
                movieEntity.toModel(videoKey = videoKeyJob?.await(), movieCasts = castJob?.await())
            }
        }
    }

    private suspend fun getVideoKeyAsync(movieEntity: MovieEntity?): Deferred<String>? {
        return coroutineScope {
            movieEntity?.let { entity ->
                if (entity.video.isEmpty()) {
                    async {
                        return@async movieDetailsRepository.getMovieVideo(entity.id).let { state ->
                            when (state) {
                                is Error -> EMPTY_STRING
                                is Success -> state.data ?: EMPTY_STRING
                            }
                        }
                    }
                } else {
                    null
                }
            }
        }
    }

    private suspend fun getCastsAsync(movieEntity: MovieEntity?): Deferred<List<CastModel>>? {
        return coroutineScope {
            movieEntity?.let { entity ->
                if (entity.casts.isEmpty()) {
                    async {
                        return@async movieDetailsRepository.getMovieCast(entity.id).let { state ->
                            when (state) {
                                is Error -> emptyList()
                                is Success -> state.data?.map { it.toModel() } ?: emptyList()
                            }
                        }
                    }
                } else {
                    null
                }
            }
        }
    }
}
