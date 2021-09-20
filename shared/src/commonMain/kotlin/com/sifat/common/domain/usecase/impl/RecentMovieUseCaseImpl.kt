package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.RecentRepository
import com.sifat.common.domain.usecase.RecentMovieUseCase

class RecentMovieUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), RecentMovieUseCase {
    override suspend fun execute(page: Int): DataState<List<ShowModel>> {
        return getDataState(recentRepository.getRecentMovieList(page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
