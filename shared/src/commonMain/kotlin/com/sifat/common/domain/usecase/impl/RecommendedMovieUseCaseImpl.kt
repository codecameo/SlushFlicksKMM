package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.MovieDetailsRepository
import com.sifat.common.domain.usecase.RecommendedMovieUseCase

class RecommendedMovieUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), RecommendedMovieUseCase {
    override suspend fun execute(
        movieId: Long,
        page: Int
    ): DataState<List<ShowModel>> {
        return getDataState(movieDetailsRepository.getRecommendMovies(movieId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
