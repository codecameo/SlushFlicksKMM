package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.CollectionModel

interface TvCollectionUseCase {
    suspend fun getTvShowCollection(): DataState<List<CollectionModel>>
}
