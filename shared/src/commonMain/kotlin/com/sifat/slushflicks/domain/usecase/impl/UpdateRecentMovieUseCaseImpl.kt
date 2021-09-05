package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.domain.repository.RecentRepository
import com.sifat.slushflicks.domain.usecase.UpdateRecentMovieUseCase

class UpdateRecentMovieUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), UpdateRecentMovieUseCase {
    override suspend fun execute(movieId: Long) {
        recentRepository.updateRecentMovie(movieId = movieId)
    }
}
