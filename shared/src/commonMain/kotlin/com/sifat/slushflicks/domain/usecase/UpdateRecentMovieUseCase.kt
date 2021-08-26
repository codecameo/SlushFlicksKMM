package com.sifat.slushflicks.domain.usecase

interface UpdateRecentMovieUseCase {
    suspend fun updateRecentMovie(movieId: Long)
}
