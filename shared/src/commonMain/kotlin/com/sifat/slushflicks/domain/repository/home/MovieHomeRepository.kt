package com.sifat.slushflicks.domain.repository.home

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.state.DataState

interface MovieHomeRepository {
    fun getMovieCollection(): DataState<List<CollectionEntity>>
}
