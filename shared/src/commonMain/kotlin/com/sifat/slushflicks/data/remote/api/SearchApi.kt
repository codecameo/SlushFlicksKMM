package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.MovieListApiModel
import com.sifat.slushflicks.data.remote.model.TvListApiModel

interface SearchApi {
    suspend fun getSearchMovies(query: String, page: Int): ApiResponse<MovieListApiModel>

    suspend fun getSearchTvShows(query: String, page: Int): ApiResponse<TvListApiModel>
}
