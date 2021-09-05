package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.TvDetailsRepository
import com.sifat.slushflicks.domain.usecase.SimilarTvShowUseCase

class SimilarTvShowUseCaseImpl(
    private val tvDetailsRepository: TvDetailsRepository
) : BaseUseCase(), SimilarTvShowUseCase {
    override suspend fun execute(tvShowId: Long, page: Int): DataState<List<ShowModel>> {
        return getDataState(tvDetailsRepository.getSimilarTvShows(tvShowId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
