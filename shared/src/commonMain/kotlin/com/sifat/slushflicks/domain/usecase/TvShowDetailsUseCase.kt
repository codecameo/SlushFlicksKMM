package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.TvShowModel

interface TvShowDetailsUseCase {
    suspend fun getTvShowDetails(tvShowId: Long): DataState<TvShowModel>
}
