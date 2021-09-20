package com.sifat.common.domain.usecase

interface UpdateRecentTvShowUseCase {
    suspend fun execute(tvShowId: Long)
}
