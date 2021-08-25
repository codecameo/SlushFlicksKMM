package com.sifat.slushflicks.data.cache.manager.impl

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.MovieCollectionEntity
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.TvCollectionEntity
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.manager.DatabaseManager
import com.sifat.slushflicks.data.cache.manager.FireStoreManager
import com.sifat.slushflicks.data.cache.manager.LocalDataManager
import com.sifat.slushflicks.data.cache.manager.SessionDataManager
import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.state.DataState

class LocalDataManagerImpl(
    private val databaseManager: DatabaseManager,
    private val sessionDataManager: SessionDataManager,
    private val fireStoreManager: FireStoreManager
) : LocalDataManager {
    override suspend fun saveGenre(genres: List<GenreEntity>) {
        databaseManager.saveGenre(genres)
    }

    override suspend fun loadGenres(): List<GenreEntity> {
        return databaseManager.loadGenres()
    }

    override suspend fun insertNewMovieCollection(
        collection: String,
        collectionModels: List<MovieCollectionEntity>
    ) {
        databaseManager.insertNewMovieCollection(collection, collectionModels)
    }

    override suspend fun insertNewMovieCollection(movie: MovieCollectionEntity) {
        databaseManager.insertNewMovieCollection(movie)
    }

    override suspend fun insertNewTvCollection(
        collection: String,
        collectionModels: List<TvCollectionEntity>
    ) {
        databaseManager.insertNewTvCollection(collection, collectionModels)
    }

    override suspend fun insertNewTvCollection(tvShow: TvCollectionEntity) {
        databaseManager.insertNewTvCollection(tvShow)
    }

    override suspend fun softInsertMovie(movies: List<MovieEntity>) {
        databaseManager.softInsertMovie(movies)
    }

    override suspend fun softInsertTv(tvShows: List<TvShowEntity>) {
        databaseManager.softInsertTv(tvShows)
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionEntity>) {
        databaseManager.addMovieCollection(collectionModels)
    }

    override suspend fun addTvCollection(collectionModels: List<TvCollectionEntity>) {
        databaseManager.addTvCollection(collectionModels)
    }

    override suspend fun getMovieDetails(movieId: Long): MovieEntity? {
        return databaseManager.getMovieDetails(movieId)
    }

    override suspend fun insertMovieDetails(movie: MovieEntity) {
        databaseManager.insertMovieDetails(movie)
    }

    override suspend fun updateMovieDetails(model: MovieEntity) {
        databaseManager.updateMovieDetails(model)
    }

    override suspend fun updateMovieDetails(videoKey: String, movieId: Long) {
        databaseManager.updateMovieDetails(videoKey, movieId)
    }

    override suspend fun updateMovieDetails(casts: List<CastColumn>, movieId: Long) {
        databaseManager.updateMovieDetails(casts, movieId)
    }

    override suspend fun insertTvShowDetails(tvShow: TvShowEntity) {
        databaseManager.insertTvShowDetails(tvShow)
    }

    override suspend fun getTvShowDetails(tvShowId: Long): TvShowEntity? {
        return databaseManager.getTvShowDetails(tvShowId)
    }

    override suspend fun updateTvDetails(model: TvShowEntity) {
        databaseManager.updateTvDetails(model)
    }

    override suspend fun updateTvDetails(casts: List<CastColumn>, tvShowId: Long) {
        databaseManager.updateTvDetails(casts, tvShowId)
    }

    override suspend fun updateTvDetails(videoKey: String, tvShowId: Long) {
        databaseManager.updateTvDetails(videoKey, tvShowId)
    }

    override suspend fun getPagingMovies(collection: String, page: Int): List<ShowEntity> {
        return databaseManager.getPagingMovies(collection, page)
    }

    override suspend fun getPagingTvShows(collection: String, page: Int): List<ShowEntity> {
        return databaseManager.getPagingTvShows(collection, page)
    }

    override fun addGenre(id: Long, name: String) {
        sessionDataManager.addGenre(id, name)
    }

    override fun addGenre(genres: List<GenreEntity>) {
        sessionDataManager.addGenre(genres)
    }

    override fun getGenre(id: Long): String? {
        return sessionDataManager.getGenre(id)
    }

    override fun initGenres(genres: List<GenreEntity>?) {
        sessionDataManager.initGenres(genres)
    }

    override fun getGenres(): Map<Long, String> {
        return sessionDataManager.getGenres()
    }

    override suspend fun getMovieCollections(): DataState<List<CollectionEntity>> {
        return fireStoreManager.getMovieCollections()
    }

    override suspend fun getTvCollections(): DataState<List<CollectionEntity>> {
        return fireStoreManager.getTvCollections()
    }
}
