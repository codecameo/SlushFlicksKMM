package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.RecentRepository
import com.sifat.slushflicks.domain.usecase.RecentMovieUseCase

class RecentMovieUseCaseImpl(
    private val recentRepository: RecentRepository
) : BaseUseCase(), RecentMovieUseCase {
    override suspend fun execute(page: Int): DataState<List<ShowModel>> {
        return getDataState(recentRepository.getRecentMovieList(page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
