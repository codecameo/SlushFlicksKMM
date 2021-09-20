package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.ShowModel

interface RecentTvShowUseCase {
    suspend fun execute(page: Int): DataState<List<ShowModel>>
}
