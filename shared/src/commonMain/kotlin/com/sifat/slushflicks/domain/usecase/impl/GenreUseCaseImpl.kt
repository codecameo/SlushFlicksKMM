package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.mapper.toModel
import com.sifat.slushflicks.domain.model.GenreModel
import com.sifat.slushflicks.domain.repository.GenreRepository
import com.sifat.slushflicks.domain.usecase.GenreUseCase

class GenreUseCaseImpl(
    private val genreRepository: GenreRepository
) : BaseUseCase(), GenreUseCase {
    override suspend fun execute(): DataState<List<GenreModel>> {
        return getDataState(genreRepository.updateGenres()) { list ->
            list?.map { it.toModel() }
        }
    }
}
