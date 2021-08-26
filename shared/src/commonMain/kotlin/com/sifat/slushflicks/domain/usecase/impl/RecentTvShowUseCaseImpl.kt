package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.RecentRepository
import com.sifat.slushflicks.domain.usecase.RecentTvShowUseCase

class RecentTvShowUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), RecentTvShowUseCase {
    override suspend fun getRecentTvShows(page: Int): DataState<List<ShowModel>> {
        return getDataState(recentRepository.getRecentTvShowList(page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
