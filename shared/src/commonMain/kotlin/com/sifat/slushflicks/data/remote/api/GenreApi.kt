package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.GenreListApiModel

interface GenreApi {
    fun getTvGenre(apiKey: String): ApiResponse<GenreListApiModel>

    fun getMovieGenre(apiKey: String): ApiResponse<GenreListApiModel>
}
