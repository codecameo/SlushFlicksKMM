package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel

interface RecentTvShowUseCase {
    suspend fun execute(page: Int): DataState<List<ShowModel>>
}
