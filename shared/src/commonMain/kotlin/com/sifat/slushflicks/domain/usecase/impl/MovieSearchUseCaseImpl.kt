package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.model.SearchResult
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.repository.SearchRepository
import com.sifat.slushflicks.domain.usecase.MovieSearchUseCase

class MovieSearchUseCaseImpl(
    private val searchRepository: SearchRepository
) : BaseUseCase(), MovieSearchUseCase {
    override suspend fun execute(query: String, page: Int): DataState<SearchResult<ShowModel>> {
        return getDataState(searchRepository.searchMovies(query, page)) { result ->
            SearchResult(
                query = query,
                page = page,
                result = result?.result?.map { it.toModel() } ?: emptyList()
            )
        }
    }
}
