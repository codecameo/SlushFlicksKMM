package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel

interface GetMovieListUseCase {
    suspend fun getMovieList(collection: String, page: Int): DataState<List<ShowModel>>
}
