package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.GenreModel

interface GenreUseCase {
    suspend fun execute(): DataState<List<GenreModel>>
}
