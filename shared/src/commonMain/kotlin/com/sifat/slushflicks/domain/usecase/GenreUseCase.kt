package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.GenreModel

interface GenreUseCase {
    suspend fun execute(): DataState<List<GenreModel>>
}
