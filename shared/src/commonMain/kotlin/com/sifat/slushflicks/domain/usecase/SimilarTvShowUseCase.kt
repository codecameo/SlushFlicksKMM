package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel

interface SimilarTvShowUseCase {
    suspend fun getSimilarTvShow(tvShowId: Long, page: Int): DataState<List<ShowModel>>
}
