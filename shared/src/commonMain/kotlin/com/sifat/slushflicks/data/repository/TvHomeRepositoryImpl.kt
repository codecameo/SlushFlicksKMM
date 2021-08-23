package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.TvHomeRepository

class TvHomeRepositoryImpl(
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvHomeRepository {
    override suspend fun getTvCollection(): DataState<List<CollectionEntity>> {
        return localDataManager.getTvCollections()
    }
}
