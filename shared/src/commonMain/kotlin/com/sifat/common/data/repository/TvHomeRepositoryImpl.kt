package com.sifat.common.data.repository

import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.repository.TvHomeRepository

class TvHomeRepositoryImpl(
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvHomeRepository {
    override suspend fun getTvCollection(): DataState<List<CollectionEntity>> {
        return localDataManager.getTvCollections()
    }
}
