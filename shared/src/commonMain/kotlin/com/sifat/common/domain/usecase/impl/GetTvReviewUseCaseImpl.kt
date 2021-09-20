package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ReviewModel
import com.sifat.common.domain.repository.TvDetailsRepository
import com.sifat.common.domain.usecase.GetTvReviewUseCase

class GetTvReviewUseCaseImpl(
    private val tvDetailsRepository: TvDetailsRepository
) : BaseUseCase(), GetTvReviewUseCase {
    override suspend fun execute(tvShowId: Long, page: Int): DataState<List<ReviewModel>> {
        return getDataState(tvDetailsRepository.getTvShowReviews(tvShowId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
