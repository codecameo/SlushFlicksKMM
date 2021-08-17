package com.sifat.slushflicks.data.remote.api

import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.model.MovieListApiModel
import com.sifat.slushflicks.data.remote.model.TvListApiModel

interface SearchApi {
    fun getSearchMovies(query: String, apiKey: String, page: Int): ApiResponse<MovieListApiModel>

    fun getSearchTvShows(query: String, apiKey: String, page: Int): ApiResponse<TvListApiModel>
}
