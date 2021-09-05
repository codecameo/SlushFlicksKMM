package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.TvDetailsRepository
import com.sifat.slushflicks.domain.usecase.RecommendedTvShowUseCase

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
