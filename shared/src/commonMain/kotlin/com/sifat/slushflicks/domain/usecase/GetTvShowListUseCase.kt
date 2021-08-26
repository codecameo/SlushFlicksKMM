package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel

interface GetTvShowListUseCase {
    suspend fun getTvShowList(collection: String, page: Int): DataState<List<ShowModel>>
}
