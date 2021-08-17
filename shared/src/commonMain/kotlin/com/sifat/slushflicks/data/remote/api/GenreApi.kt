package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.GenreListApiModel

interface GenreApi {
    suspend fun getTvGenre(): ApiResponse<GenreListApiModel>

    suspend fun getMovieGenre(): ApiResponse<GenreListApiModel>
}
