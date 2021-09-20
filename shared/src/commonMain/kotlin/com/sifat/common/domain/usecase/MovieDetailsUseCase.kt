package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsUseCase {
    suspend fun execute(movieId: Long): Flow<DataState<MovieModel>>
}
