package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.MovieModel

interface MovieDetailsUseCase {
    suspend fun getMovieDetails(movieId: Long): DataState<MovieModel>
}
