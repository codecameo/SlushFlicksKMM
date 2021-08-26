package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.MovieListRepository
import com.sifat.slushflicks.domain.usecase.GetMovieListUseCase

class GetMovieListUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : BaseUseCase(), GetMovieListUseCase {
    override suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowModel>> {
        return getDataState(movieListRepository.getMovieList(collection, page)) { list ->
            list?.map { it.toModel() }
        }
    }
}
