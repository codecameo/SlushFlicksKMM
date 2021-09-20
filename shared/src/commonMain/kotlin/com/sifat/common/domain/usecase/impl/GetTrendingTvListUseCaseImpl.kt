package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.TvListRepository
import com.sifat.common.domain.usecase.GetTrendingTvListUseCase

class GetTrendingTvListUseCaseImpl(
    private val tvListRepository: TvListRepository
) : BaseUseCase(), GetTrendingTvListUseCase {
    override suspend fun execute(collection: String, page: Int): DataState<List<ShowModel>> {
        return getDataState(tvListRepository.getTvList(collection, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
