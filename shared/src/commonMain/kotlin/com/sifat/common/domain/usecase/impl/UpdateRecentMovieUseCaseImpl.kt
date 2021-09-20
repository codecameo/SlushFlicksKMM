package com.sifat.common.domain.usecase.impl

import com.sifat.common.domain.repository.RecentRepository
import com.sifat.common.domain.usecase.UpdateRecentMovieUseCase

class UpdateRecentMovieUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), UpdateRecentMovieUseCase {
    override suspend fun execute(movieId: Long) {
        recentRepository.updateRecentMovie(movieId = movieId)
    }
}
