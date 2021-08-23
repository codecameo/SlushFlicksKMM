package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

interface MovieListRepository {
    suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowEntity>>
}
