package com.sifat.slushflicks.domain.usecase

interface UpdateRecentMovieUseCase {
    suspend fun execute(movieId: Long)
}
