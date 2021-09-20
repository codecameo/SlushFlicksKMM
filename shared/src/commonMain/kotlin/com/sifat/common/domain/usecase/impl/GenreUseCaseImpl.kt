package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.mapper.toModel
import com.sifat.common.domain.model.GenreModel
import com.sifat.common.domain.repository.GenreRepository
import com.sifat.common.domain.usecase.GenreUseCase

class GenreUseCaseImpl(
    private val genreRepository: GenreRepository
) : BaseUseCase(), GenreUseCase {
    override suspend fun execute(): DataState<List<GenreModel>> {
        return getDataState(genreRepository.updateGenres()) { list ->
            list?.map { it.toModel() }
        }
    }
}
