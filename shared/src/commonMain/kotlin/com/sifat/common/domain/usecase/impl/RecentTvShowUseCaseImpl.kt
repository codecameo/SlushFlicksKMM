package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.RecentRepository
import com.sifat.common.domain.usecase.RecentTvShowUseCase

class RecentTvShowUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), RecentTvShowUseCase {
    override suspend fun execute(page: Int): DataState<List<ShowModel>> {
        return getDataState(recentRepository.getRecentTvShowList(page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
