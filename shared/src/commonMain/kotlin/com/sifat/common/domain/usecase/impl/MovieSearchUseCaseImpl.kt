package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.model.SearchResult
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.repository.SearchRepository
import com.sifat.common.domain.usecase.MovieSearchUseCase

class MovieSearchUseCaseImpl(
    private val searchRepository: SearchRepository
) : BaseUseCase(), MovieSearchUseCase {
    override suspend fun execute(query: String, page: Int): DataState<SearchResult<ShowModel>> {
        return if (query.isBlank()) {
            DataState.Success(
                data = SearchResult(
                    query = query,
                    page = page,
                    result = emptyList()
                )
            )
        } else {
            getDataState(searchRepository.searchMovies(query, page)) { result ->
                SearchResult(
                    query = query,
                    page = page,
                    result = result?.result?.map { it.toModel() } ?: emptyList()
                )
            }
        }
    }
}
