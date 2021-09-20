package com.sifat.common.domain.repository

import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.data.state.DataState

interface MovieHomeRepository {
    suspend fun getMovieCollection(): DataState<List<CollectionEntity>>
}
