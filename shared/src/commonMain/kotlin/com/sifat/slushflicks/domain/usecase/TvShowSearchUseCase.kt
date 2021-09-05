package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.model.SearchResult
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel

interface TvShowSearchUseCase {
    suspend fun execute(query: String, page: Int): DataState<SearchResult<ShowModel>>
}
