package com.sifat.slushflicks.domain.usecase

interface UpdateRecentTvShowUseCase {
    suspend fun execute(tvShowId: Long)
}
