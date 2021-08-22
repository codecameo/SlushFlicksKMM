package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.TvDetailsRepository

class TvDetailsRepositoryImpl : TvDetailsRepository {
    override fun getTvShowDetails(tvShowId: Long): DataState<TvShowEntity> {
        TODO("Not yet implemented")
    }

    override fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): DataState<String> {
        TODO("Not yet implemented")
    }

    override fun getTvShowCast(tvShowId: Long): DataState<Int> {
        TODO("Not yet implemented")
    }

    override fun getSimilarTvShows(tvShowId: Long): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getRecommendationTvShows(tvShowId: Long): DataState<List<ShowEntity>> {
        TODO("Not yet implemented")
    }

    override fun getTvShowReviews(tvShowId: Long): DataState<List<ReviewApiModel>> {
        TODO("Not yet implemented")
    }

    override fun updateRecentTvShow(tvShowId: Long) {
        TODO("Not yet implemented")
    }
}
