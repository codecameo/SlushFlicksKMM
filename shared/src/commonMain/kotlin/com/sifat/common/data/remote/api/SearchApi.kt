package com.sifat.common.data.remote.api

import com.sifat.common.data.remote.ApiResponse
import com.sifat.common.data.remote.model.MovieListApiModel
import com.sifat.common.data.remote.model.TvListApiModel

interface SearchApi {
    suspend fun getSearchMovies(query: String, page: Int): ApiResponse<MovieListApiModel>

    suspend fun getSearchTvShows(query: String, page: Int): ApiResponse<TvListApiModel>
}
