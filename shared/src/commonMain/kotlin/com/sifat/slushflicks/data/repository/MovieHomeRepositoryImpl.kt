package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.MovieHomeRepository

class MovieHomeRepositoryImpl : MovieHomeRepository {
    override fun getMovieCollection(): DataState<List<CollectionEntity>> {
        TODO("Not yet implemented")
    }
}
