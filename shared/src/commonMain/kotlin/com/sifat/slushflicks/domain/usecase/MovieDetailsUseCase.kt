package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsUseCase {
    suspend fun execute(movieId: Long): Flow<DataState<MovieModel>>
}
