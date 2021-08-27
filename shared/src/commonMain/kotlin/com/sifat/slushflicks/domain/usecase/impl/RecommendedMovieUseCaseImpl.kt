package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import com.sifat.slushflicks.domain.usecase.RecommendedMovieUseCase

class RecommendedMovieUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), RecommendedMovieUseCase {
    override suspend fun getRecommendedMovies(
        movieId: Long,
        page: Int
    ): DataState<List<ShowModel>> {
        return getDataState(movieDetailsRepository.getRecommendMovies(movieId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
