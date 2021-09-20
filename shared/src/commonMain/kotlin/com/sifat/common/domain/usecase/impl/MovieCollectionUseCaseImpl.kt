package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.CollectionModel
import com.sifat.common.domain.repository.MovieHomeRepository
import com.sifat.common.domain.usecase.MovieCollectionUseCase

class MovieCollectionUseCaseImpl(
    private val movieHomeRepository: MovieHomeRepository
) : BaseUseCase(), MovieCollectionUseCase {
    override suspend fun execute(): DataState<List<CollectionModel>> {
        return getDataState(movieHomeRepository.getMovieCollection()) { list ->
            list?.map { it.toModel() }
        }
    }
}
