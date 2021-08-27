package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel

interface RecommendedTvShowUseCase {
    suspend fun getRecommendedTvShow(tvShowId: Long, page: Int): DataState<List<ShowModel>>
}
