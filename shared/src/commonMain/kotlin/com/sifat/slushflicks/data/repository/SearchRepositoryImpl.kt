package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.SearchRepository

class SearchRepositoryImpl : SearchRepository {
    override fun searchMovies(query: String, page: Int): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun searchTvShows(query: String, page: Int): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getRecentMovieList(page: Int): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getRecentTvShowList(page: Int): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }
}
