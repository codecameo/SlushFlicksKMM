package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.MovieHomeRepository

class MovieHomeRepositoryImpl(
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieHomeRepository {
    override fun getMovieCollection(): DataState<List<CollectionEntity>> {
        TODO("Not yet implemented")
    }
}
