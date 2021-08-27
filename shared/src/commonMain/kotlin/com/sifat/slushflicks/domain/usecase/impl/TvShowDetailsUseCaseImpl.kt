package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.Constants
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Error
import com.sifat.slushflicks.data.state.DataState.Success
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.CastModel
import com.sifat.slushflicks.domain.model.TvShowModel
import com.sifat.slushflicks.domain.repository.TvDetailsRepository
import com.sifat.slushflicks.domain.usecase.TvShowDetailsUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class TvShowDetailsUseCaseImpl(
    private val tvDetailsRepository: TvDetailsRepository
) : BaseUseCase(), TvShowDetailsUseCase {
    override suspend fun getTvShowDetails(tvShowId: Long): DataState<TvShowModel> {
        return tvDetailsRepository.getTvShowDetails(tvShowId).let { state ->
            when (state) {
                is Error -> getErrorResponse(state) {
                    it?.toModel()
                }
                is Success -> Success(
                    data = getTvShowDetails(state.data),
                    message = state.message
                )
            }
        }
    }

    private suspend fun getTvShowDetails(tvShowEntity: TvShowEntity?): TvShowModel? {
        return tvShowEntity?.let {
            coroutineScope {
                val videoKeyJob = getVideoKeyAsync(tvShowEntity)
                val castJob = getCastsAsync(tvShowEntity)
                tvShowEntity.toModel(videoKey = videoKeyJob?.await(), movieCasts = castJob?.await())
            }
        }
    }

    private suspend fun getVideoKeyAsync(tvShowEntity: TvShowEntity?): Deferred<String>? {
        return coroutineScope {
            tvShowEntity?.let { entity ->
                if (entity.video.isEmpty()) {
                    async {
                        return@async tvDetailsRepository.getTvShowVideo(
                            entity.id,
                            entity.numOfSeason
                        ).let { state ->
                            when (state) {
                                is Error -> Constants.EMPTY_STRING
                                is Success -> state.data ?: Constants.EMPTY_STRING
                            }
                        }
                    }
                } else {
                    null
                }
            }
        }
    }

    private suspend fun getCastsAsync(tvShowEntity: TvShowEntity?): Deferred<List<CastModel>>? {
        return coroutineScope {
            tvShowEntity?.let { entity ->
                if (entity.casts.isEmpty()) {
                    async {
                        return@async tvDetailsRepository.getTvShowCast(entity.id).let { state ->
                            when (state) {
                                is Error -> emptyList()
                                is Success -> state.data?.map { it.toModel() }
                                    ?: emptyList()
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
