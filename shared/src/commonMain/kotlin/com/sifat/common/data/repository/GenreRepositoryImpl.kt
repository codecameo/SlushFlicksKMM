package com.sifat.common.data.repository

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.mapper.toEntity
import com.sifat.common.data.remote.ApiSuccessResponse
import com.sifat.common.data.remote.StatusCode.NO_INTERNET_ERROR
import com.sifat.common.data.remote.api.GenreApi
import com.sifat.common.data.remote.model.GenreListApiModel
import com.sifat.common.data.state.DataState
import com.sifat.common.data.state.DataState.Error
import com.sifat.common.domain.repository.GenreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GenreRepositoryImpl(
    private val localDataManager: LocalDataManager,
    private val genreApi: GenreApi,
    networkStateManager: NetworkStateManager
) : BaseRepository(networkStateManager), GenreRepository {

    override suspend fun updateGenres(): DataState<List<GenreEntity>> {
        return execute(fallback = {
            localDataManager.loadGenres().let { genres ->
                updateGenresSessionData(genres)
                Error(statusCode = NO_INTERNET_ERROR, data = genres)
            }
        }) {
            coroutineScope {
                val movieGenre = async {
                    genreApi.getMovieGenre()
                }
                val tvGenreGenre = async {
                    genreApi.getTvGenre()
                }
                val genres = mutableListOf<GenreEntity>()
                (movieGenre.await() as? ApiSuccessResponse)?.let { genres.addAll(getGenreList(it)) }
                (tvGenreGenre.await() as? ApiSuccessResponse)?.let { genres.addAll(getGenreList(it)) }
                localDataManager.saveGenre(genres)
                updateGenresSessionData(genres)
                DataState.Success(data = genres)
            }
        }
    }

    private fun updateGenresSessionData(list: List<GenreEntity>?) {
        localDataManager.initGenres(list)
    }

    private fun getGenreList(response: ApiSuccessResponse<GenreListApiModel>): List<GenreEntity> {
        return response.data?.genres?.map { it.toEntity() } ?: emptyList()
    }
}
