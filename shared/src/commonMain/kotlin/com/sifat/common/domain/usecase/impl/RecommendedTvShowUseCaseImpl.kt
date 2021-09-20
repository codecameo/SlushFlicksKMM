package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.TvDetailsRepository
import com.sifat.common.domain.usecase.RecommendedTvShowUseCase

class RecommendedTvShowUseCaseImpl(
    private val tvDetailsRepository: TvDetailsRepository
) : BaseUseCase(), RecommendedTvShowUseCase {
    override suspend fun execute(
        tvShowId: Long,
        page: Int
    ): DataState<List<ShowModel>> {
        return getDataState(tvDetailsRepository.getRecommendationTvShows(tvShowId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
