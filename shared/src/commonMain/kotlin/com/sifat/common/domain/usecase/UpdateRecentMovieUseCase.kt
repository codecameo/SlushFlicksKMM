package com.sifat.common.domain.usecase

interface UpdateRecentMovieUseCase {
    suspend fun execute(movieId: Long)
}
