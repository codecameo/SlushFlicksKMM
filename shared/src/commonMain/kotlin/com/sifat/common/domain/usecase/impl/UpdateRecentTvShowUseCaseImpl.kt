package com.sifat.common.domain.usecase.impl

import com.sifat.common.domain.repository.RecentRepository
import com.sifat.common.domain.usecase.UpdateRecentTvShowUseCase

class UpdateRecentTvShowUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), UpdateRecentTvShowUseCase {
    override suspend fun execute(tvShowId: Long) {
        recentRepository.updateRecentTvShow(tvShowId = tvShowId)
    }
}
