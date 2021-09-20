package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.MovieDetailsRepository
import com.sifat.common.domain.usecase.SimilarMovieUseCase

class SimilarMovieUseCaseImpl(
    private val movieDetailsRepository: MovieDetailsRepository
) : BaseUseCase(), SimilarMovieUseCase {
    override suspend fun execute(movieId: Long, page: Int): DataState<List<ShowModel>> {
        return getDataState(movieDetailsRepository.getSimilarMovies(movieId, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
