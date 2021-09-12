package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow

interface TvShowDetailsUseCase {
    suspend fun execute(tvShowId: Long): Flow<DataState<TvShowModel>>
}
