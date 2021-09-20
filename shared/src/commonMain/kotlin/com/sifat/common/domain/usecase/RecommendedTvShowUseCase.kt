package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.ShowModel

interface RecommendedTvShowUseCase {
    suspend fun execute(tvShowId: Long, page: Int): DataState<List<ShowModel>>
}
