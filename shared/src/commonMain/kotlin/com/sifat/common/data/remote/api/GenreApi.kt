package com.sifat.common.data.remote.api

import com.sifat.common.data.remote.ApiResponse
import com.sifat.common.data.remote.model.GenreListApiModel

interface GenreApi {
    suspend fun getTvGenre(): ApiResponse<GenreListApiModel>

    suspend fun getMovieGenre(): ApiResponse<GenreListApiModel>
}
