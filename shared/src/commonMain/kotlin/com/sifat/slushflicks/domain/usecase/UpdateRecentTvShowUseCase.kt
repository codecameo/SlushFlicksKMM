package com.sifat.slushflicks.domain.usecase

interface UpdateRecentTvShowUseCase {
    suspend fun updateRecentTvShow(tvShowId: Long)
}
