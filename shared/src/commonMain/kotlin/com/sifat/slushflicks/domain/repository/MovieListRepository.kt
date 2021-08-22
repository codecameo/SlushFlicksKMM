package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

interface MovieListRepository {
    val collection: String

    fun getMovieList(nextPage: Int): DataState<List<ShowEntity>>
}
