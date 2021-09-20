package com.sifat.common.domain.usecase

import com.sifat.common.data.model.SearchResult
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.ShowModel

interface TvShowSearchUseCase {
    suspend fun execute(query: String, page: Int): DataState<SearchResult<ShowModel>>
}
