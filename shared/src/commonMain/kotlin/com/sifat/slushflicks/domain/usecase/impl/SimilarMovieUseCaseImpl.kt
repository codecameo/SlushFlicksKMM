package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import com.sifat.slushflicks.domain.usecase.SimilarMovieUseCase

class SimilarMovieUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), SimilarMovieUseCase {
    override suspend fun getSimilarMovies(movieId: Long, page: Int): DataState<List<ShowModel>> {
        return getDataState(movieDetailsRepository.getSimilarMovies(movieId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
