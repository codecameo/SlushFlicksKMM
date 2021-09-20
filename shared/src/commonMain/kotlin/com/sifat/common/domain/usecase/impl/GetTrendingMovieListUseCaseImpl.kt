package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.MovieListRepository
import com.sifat.common.domain.usecase.GetTrendingMovieListUseCase

class GetTrendingMovieListUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : BaseUseCase(), GetTrendingMovieListUseCase {
    override suspend fun execute(collection: String, page: Int): DataState<List<ShowModel>> {
        return getDataState(movieListRepository.getMovieList(collection, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
