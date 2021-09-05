package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.domain.repository.RecentRepository
import com.sifat.slushflicks.domain.usecase.UpdateRecentTvShowUseCase

class UpdateRecentTvShowUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), UpdateRecentTvShowUseCase {
    override suspend fun execute(tvShowId: Long) {
        recentRepository.updateRecentTvShow(tvShowId = tvShowId)
    }
}
