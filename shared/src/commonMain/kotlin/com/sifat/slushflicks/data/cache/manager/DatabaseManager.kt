package com.sifat.slushflicks.data.cache.manager

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.MovieCollectionEntity
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.TvCollectionEntity
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.model.ShowEntity

interface DatabaseManager {

    suspend fun saveGenre(genres: List<GenreEntity>)

    suspend fun loadGenres(): List<GenreEntity>

    suspend fun insertNewMovieCollection(
        collection: String,
        collectionModels: List<MovieCollectionEntity>
    )

    suspend fun insertNewMovieCollection(movie: MovieCollectionEntity)

    suspend fun insertNewTvCollection(
        collection: String,
        collectionModels: List<TvCollectionEntity>
    )

    suspend fun insertNewTvCollection(tvShow: TvCollectionEntity)

    suspend fun softInsertMovie(movies: List<MovieEntity>)

    suspend fun softInsertTv(tvShows: List<TvShowEntity>)

    suspend fun addMovieCollection(collectionModels: List<MovieCollectionEntity>)

    suspend fun addTvCollection(collectionModels: List<TvCollectionEntity>)

    suspend fun getMovieDetails(movieId: Long): MovieEntity?

    suspend fun insertMovieDetails(movie: MovieEntity)

    suspend fun updateMovieDetails(model: MovieEntity)

    suspend fun updateMovieDetails(videoKey: String, movieId: Long)

    suspend fun updateMovieDetails(casts: List<CastColumn>, movieId: Long)

    suspend fun insertTvShowDetails(tvShow: TvShowEntity)

    suspend fun getTvShowDetails(tvShowId: Long): TvShowEntity?

    suspend fun updateTvDetails(model: TvShowEntity)

    suspend fun updateTvDetails(casts: List<CastColumn>, tvShowId: Long)

    suspend fun updateTvDetails(videoKey: String, tvShowId: Long)

    suspend fun getPagingMovies(collection: String, page: Int): List<ShowEntity>

    suspend fun getPagingTvShows(collection: String, page: Int): List<ShowEntity>
}
