package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow

interface TvShowDetailsUseCase {
    suspend fun execute(tvShowId: Long): Flow<DataState<TvShowModel>>
}
