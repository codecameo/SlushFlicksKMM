package com.sifat.common.data.repository

import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.Label.RECOMMENDATION_LABEL
import com.sifat.common.data.Label.SIMILAR_LABEL
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.common.data.cache.column.CastColumn
import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.isYoutubeModel
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.mapper.toColumn
import com.sifat.common.data.mapper.toEntity
import com.sifat.common.data.remote.api.TvShowApi
import com.sifat.common.data.remote.model.ReviewApiModel
import com.sifat.common.data.state.DataState
import com.sifat.common.data.state.DataState.Error
import com.sifat.common.data.state.DataState.Success
import com.sifat.common.domain.repository.TvDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TvDetailsRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val localDataManager: LocalDataManager,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), TvDetailsRepository {
    override suspend fun getTvShowDetails(tvShowId: Long): Flow<DataState<TvShowEntity>> {
        return flow {
            localDataManager.getTvShowDetails(tvShowId).also {
                emit(Success(data = it))
            }
            updateTvShowDetails(tvShowId).let { state ->
                when (state) {
                    is Success -> localDataManager.getTvShowDetails(tvShowId).also {
                        emit(Success(data = it, message = state.message))
                    }
                    is Error -> localDataManager.getTvShowDetails(tvShowId).also {
                        emit(
                            Error(
                                data = it,
                                errorMessage = state.errorMessage,
                                statusCode = state.statusCode
                            )
                        )
                    }
                }
            }
        }
    }

    override suspend fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): DataState<String> {
        return execute {
            getDataState(tvShowApi.getTvShowVideos(tvShowId, seasonNumber)) { apiModel ->
                apiModel?.results?.find { isYoutubeModel(it) }?.key ?: EMPTY_STRING
            }.also {
                (it as? Success)?.data?.let { video ->
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
                (it as? Success)?.data?.let { casts ->
                    localDataManager.updateTvDetails(casts = casts, tvShowId = tvShowId)
                }
            }
        }
    }

    override suspend fun getSimilarTvShows(tvShowId: Long, page: Int): DataState<List<ShowEntity>> {
        return execute {
            getDataState(
                tvShowApi.getRelatedTvShows(tvShowId, SIMILAR_LABEL, page)
            ) {
                val genres = localDataManager.getGenres()
                it?.results?.map { it.toEntity(genres) } ?: emptyList()
            }
        }
    }

    override suspend fun getRecommendationTvShows(
        tvShowId: Long,
        page: Int
    ): DataState<List<ShowEntity>> {
        return execute {
            getDataState(tvShowApi.getRelatedTvShows(tvShowId, RECOMMENDATION_LABEL, page)) {
                val genres = localDataManager.getGenres()
                it?.results?.map { it.toEntity(genres) } ?: emptyList()
            }
        }
    }

    override suspend fun getTvShowReviews(
        tvShowId: Long,
        page: Int
    ): DataState<List<ReviewApiModel>> {
        return execute {
            getDataState(tvShowApi.getTvShowReviews(tvShowId, page)) {
                it?.results ?: emptyList()
            }
        }
    }

    private suspend fun updateTvShowDetails(tvShowId: Long): DataState<TvShowEntity> {
        return execute {
            getDataState(tvShowApi.getTvShowDetails(tvShowId)) { it?.toEntity() }.also {
                (it as? Success)?.data?.let { tvShow ->
                    localDataManager.updateTvDetails(tvShow)
                }
            }
        }
    }
}
