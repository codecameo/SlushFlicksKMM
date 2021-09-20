package com.sifat.common.domain.repository

import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.data.state.DataState

interface TvHomeRepository {
    suspend fun getTvCollection(): DataState<List<CollectionEntity>>
}
