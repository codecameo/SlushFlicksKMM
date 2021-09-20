package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ReviewModel
import com.sifat.common.domain.repository.MovieDetailsRepository
import com.sifat.common.domain.usecase.GetMovieReviewUseCase

class GetMovieReviewUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), GetMovieReviewUseCase {
    override suspend fun execute(movieId: Long, page: Int): DataState<List<ReviewModel>> {
        return getDataState(movieDetailsRepository.getReviews(movieId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
