package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.TvHomeRepository

class TvHomeRepositoryImpl(
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvHomeRepository {
    override fun getTvCollection(): DataState<List<CollectionEntity>> {
        TODO("Not yet implemented")
    }
}
