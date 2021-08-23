package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

interface TvListRepository {
    suspend fun getTvList(collection: String, page: Int): DataState<List<ShowEntity>>
}
