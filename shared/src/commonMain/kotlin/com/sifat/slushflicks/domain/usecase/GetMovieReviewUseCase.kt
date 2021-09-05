package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ReviewModel

interface GetMovieReviewUseCase {
    suspend fun execute(movieId: Long, page: Int): DataState<List<ReviewModel>>
}
