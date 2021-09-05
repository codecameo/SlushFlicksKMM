package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.TvListRepository
import com.sifat.slushflicks.domain.usecase.GetTrendingTvListUseCase

class GetTrendingTvListUseCaseImpl(
    private val tvListRepository: TvListRepository
) : BaseUseCase(), GetTrendingTvListUseCase {
    override suspend fun execute(collection: String, page: Int): DataState<List<ShowModel>> {
        return getDataState(tvListRepository.getTvList(collection, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
