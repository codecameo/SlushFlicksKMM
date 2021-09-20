package com.sifat.common.data.cache.manager

import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.data.state.DataState

interface FireStoreManager {

    suspend fun getMovieCollections(): DataState<List<CollectionEntity>>

    suspend fun getTvCollections(): DataState<List<CollectionEntity>>
}
