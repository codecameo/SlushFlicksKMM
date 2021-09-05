package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import com.sifat.slushflicks.domain.usecase.GetMovieReviewUseCase

class GetMovieReviewUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), GetMovieReviewUseCase {
    override suspend fun execute(movieId: Long, page: Int): DataState<List<ReviewModel>> {
        return getDataState(movieDetailsRepository.getReviews(movieId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
