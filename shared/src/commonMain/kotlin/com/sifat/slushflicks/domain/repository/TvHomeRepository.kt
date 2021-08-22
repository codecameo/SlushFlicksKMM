package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.state.DataState

interface TvHomeRepository {
    fun getTvCollection(): DataState<List<CollectionEntity>>
}
