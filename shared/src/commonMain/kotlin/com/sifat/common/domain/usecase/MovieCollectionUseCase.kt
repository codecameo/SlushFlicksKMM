package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.CollectionModel

interface MovieCollectionUseCase {
    suspend fun execute(): DataState<List<CollectionModel>>
}
