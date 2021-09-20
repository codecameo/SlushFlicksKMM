package com.sifat.common.domain.repository

import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.state.DataState

interface MovieListRepository {
    suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowEntity>>
}
