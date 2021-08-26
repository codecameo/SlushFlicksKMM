package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.CollectionModel
import com.sifat.slushflicks.domain.repository.MovieHomeRepository
import com.sifat.slushflicks.domain.usecase.MovieCollectionUseCase

class MovieCollectionUseCaseImpl(
    private val movieHomeRepository: MovieHomeRepository
) : BaseUseCase(), MovieCollectionUseCase {
    override suspend fun getMovieCollection(): DataState<List<CollectionModel>> {
        return getDataState(movieHomeRepository.getMovieCollection()) { list ->
            list?.map { it.toModel() }
        }
    }
}
