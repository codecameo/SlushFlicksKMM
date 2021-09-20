package com.sifat.common.data.repository

import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.repository.MovieHomeRepository

class MovieHomeRepositoryImpl(
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieHomeRepository {
    override suspend fun getMovieCollection(): DataState<List<CollectionEntity>> {
        return localDataManager.getMovieCollections()
    }
}
