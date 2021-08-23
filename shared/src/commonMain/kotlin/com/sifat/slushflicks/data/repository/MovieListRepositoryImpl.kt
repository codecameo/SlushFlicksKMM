package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.MovieListRepository

class MovieListRepositoryImpl(
    override val collection: String,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), MovieListRepository {
    override fun getMovieList(nextPage: Int): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }
}
