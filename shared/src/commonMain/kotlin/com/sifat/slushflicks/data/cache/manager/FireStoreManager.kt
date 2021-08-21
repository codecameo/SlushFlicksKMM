package com.sifat.slushflicks.data.cache.manager

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.state.DataState

interface FireStoreManager {

    suspend fun getMovieCollections(): DataState<List<CollectionEntity>>

    suspend fun getTvCollections(): DataState<List<CollectionEntity>>
}
