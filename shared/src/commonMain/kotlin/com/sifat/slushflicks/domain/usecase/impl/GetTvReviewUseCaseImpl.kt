package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.repository.TvDetailsRepository
import com.sifat.slushflicks.domain.usecase.GetTvReviewUseCase

class GetTvReviewUseCaseImpl(
    private val tvDetailsRepository: TvDetailsRepository
) : BaseUseCase(), GetTvReviewUseCase {
    override suspend fun execute(tvShowId: Long, page: Int): DataState<List<ReviewModel>> {
        return getDataState(tvDetailsRepository.getTvShowReviews(tvShowId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
