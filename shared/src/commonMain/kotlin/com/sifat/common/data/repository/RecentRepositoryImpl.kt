package com.sifat.common.data.repository

import com.sifat.common.data.Label.RECENTLY_VISITED_MOVIE
import com.sifat.common.data.Label.RECENTLY_VISITED_TV_SHOW
import com.sifat.slushflicks.data.cache.MovieCollectionEntity
import com.sifat.slushflicks.data.cache.TvCollectionEntity
import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.manager.TimeManager
import com.sifat.common.data.state.DataState
import com.sifat.common.data.state.DataState.Success
import com.sifat.common.domain.repository.RecentRepository

class RecentRepositoryImpl(
    private val localDataManager: LocalDataManager,
    private val timeManager: TimeManager
) : RecentRepository {
    override suspend fun getRecentMovieList(page: Int): DataState<List<ShowEntity>> {
        return Success(localDataManager.getPagingMovies(RECENTLY_VISITED_MOVIE, page))
    }

    override suspend fun getRecentTvShowList(page: Int): DataState<List<ShowEntity>> {
        return Success(localDataManager.getPagingTvShows(RECENTLY_VISITED_TV_SHOW, page))
    }

    override suspend fun updateRecentMovie(movieId: Long) {
        val time = (timeManager.getCurrentTime() / 1000).toInt()
        localDataManager.insertNewMovieCollection(
            MovieCollectionEntity(
                collection = RECENTLY_VISITED_MOVIE,
                id = movieId,
                position = -1 * time // Reversing the order
            )
        )
    }

    override suspend fun updateRecentTvShow(tvShowId: Long) {
        val time = (timeManager.getCurrentTime() / 1000).toInt()
        localDataManager.insertNewTvCollection(
            TvCollectionEntity(
                collection = RECENTLY_VISITED_TV_SHOW,
                id = tvShowId,
                position = -1 * time // Reversing the order
            )
        )
    }
}
