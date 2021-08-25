package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.Constants
import com.sifat.slushflicks.data.Label
import com.sifat.slushflicks.data.Label.Companion.RECOMMENDATION_LABEL
import com.sifat.slushflicks.data.Label.Companion.SIMILAR_LABEL
import com.sifat.slushflicks.data.cache.TvCollectionEntity
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.isYoutubeModel
import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.manager.TimeManager
import com.sifat.slushflicks.data.mapper.toColumn
import com.sifat.slushflicks.data.mapper.toEntity
import com.sifat.slushflicks.data.remote.api.TvShowApi
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.TvDetailsRepository

class TvDetailsRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val localDataManager: LocalDataManager,
    private val timeManager: TimeManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvDetailsRepository {
    override suspend fun getTvShowDetails(tvShowId: Long): DataState<TvShowEntity> {
        return localDataManager.getTvShowDetails(tvShowId)?.let { entity ->
            return if (hasOtherData(entity)) DataState.Success(data = entity) else fetchTvShowDetails(
                tvShowId
            )
        } ?: fetchTvShowDetails(tvShowId)
    }

    override suspend fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): DataState<String> {
        return execute {
            getDataState(tvShowApi.getTvShowVideos(tvShowId, seasonNumber)) { apiModel ->
                apiModel?.results?.find { isYoutubeModel(it) }?.key ?: Constants.EMPTY_STRING
            }.also {
                (it as? DataState.Success)?.data?.let { video ->
                    localDataManager.updateTvDetails(videoKey = video, tvShowId = tvShowId)
                }
            }
        }
    }

    override suspend fun getTvShowCast(tvShowId: Long): DataState<List<CastColumn>> {
        return execute {
            getDataState(tvShowApi.getTvShowCredits(tvShowId)) { credits ->
                credits?.casts?.map { it.toColumn() }
            }.also {
                (it as? DataState.Success)?.data?.let { casts ->
                    localDataManager.updateTvDetails(casts = casts, tvShowId = tvShowId)
                }
            }
        }
    }

    override suspend fun getSimilarTvShows(tvShowId: Long, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(
                tvShowApi.getRelatedTvShows(tvShowId, SIMILAR_LABEL, page)
            )
        }
    }

    override suspend fun getRecommendationTvShows(
        tvShowId: Long,
        page: Int
    ): DataState<List<ShowEntity>> {
        return execute {
            getDataState(tvShowApi.getRelatedTvShows(tvShowId, RECOMMENDATION_LABEL, page))
        }
    }

    override suspend fun getTvShowReviews(
        tvShowId: Long,
        page: Int
    ): DataState<List<ReviewApiModel>> {
        return execute {
            getDataState(tvShowApi.getTvShowReviews(tvShowId, page))
        }
    }

    override suspend fun updateRecentTvShow(tvShowId: Long) {
        val time = (timeManager.getCurrentTime() / 1000).toInt()
        localDataManager.insertNewTvCollection(
            TvCollectionEntity(
                collection = Label.RECENTLY_VISITED_MOVIE,
                id = tvShowId,
                position = -1 * time // Reversing the order
            )
        )
    }

    private suspend fun fetchTvShowDetails(tvShowId: Long): DataState<TvShowEntity> {
        return execute {
            getDataState(tvShowApi.getTvShowDetails(tvShowId)) {
                it?.toEntity()
            }.also {
                (it as? DataState.Success)?.data?.let { tvShow ->
                    localDataManager.insertTvShowDetails(tvShow)
                }
            }
        }
    }

    private fun hasOtherData(entity: TvShowEntity): Boolean {
        return entity.run {
            runtime != Constants.DEFAULT_INT ||
                voteCount != Constants.DEFAULT_INT ||
                status != Constants.EMPTY_STRING ||
                posterPath != Constants.EMPTY_STRING ||
                popularity != Constants.DEFAULT_DOUBLE ||
                directors.isNotEmpty()
        }
    }
}
