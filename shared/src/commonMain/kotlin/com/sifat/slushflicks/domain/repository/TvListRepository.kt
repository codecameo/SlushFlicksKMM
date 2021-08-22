package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

interface TvListRepository {
    val collection: String

    fun getTvList(nextPage: Int): DataState<List<ShowEntity>>
}
