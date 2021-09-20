package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.ShowModel

interface GetTrendingMovieListUseCase {
    suspend fun execute(collection: String, page: Int): DataState<List<ShowModel>>
}
