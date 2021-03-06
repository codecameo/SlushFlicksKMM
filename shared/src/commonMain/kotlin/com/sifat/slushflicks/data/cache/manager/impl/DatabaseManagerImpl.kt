package com.sifat.slushflicks.data.cache.manager.impl

import com.sifat.slushflicks.data.Constants.PAGE_SIZE
import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.MovieCollectionEntity
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.SlushFlicksQueries
import com.sifat.slushflicks.data.cache.TvCollectionEntity
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.data.cache.manager.DatabaseManager
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.mapper.toEntity

class DatabaseManagerImpl(
    private val slushFlicksQueries: SlushFlicksQueries
) : DatabaseManager {
    override suspend fun saveGenre(genres: List<GenreEntity>) {
        slushFlicksQueries.run {
            for (genre in genres) {
                insertGenre(genre.id, genre.name)
            }
        }
    }

    override suspend fun loadGenres(): List<GenreEntity> {
        return slushFlicksQueries.selectAllGenre().executeAsList()
    }

    override suspend fun insertNewMovieCollection(
        collection: String,
        collectionModels: List<MovieCollectionEntity>
    ) {
        slushFlicksQueries.run {
            transaction {
                deleteMovieCollection(collection)
                for ((id, _, position) in collectionModels) {
                    insertReplaceMovieCollection(id, collection, position)
                }
            }
        }
    }

    override suspend fun insertNewMovieCollection(movie: MovieCollectionEntity) {
        slushFlicksQueries.insertReplaceMovieCollection(movie.id, movie.collection, movie.position)
    }

    override suspend fun insertNewTvCollection(
        collection: String,
        collectionModels: List<TvCollectionEntity>
    ) {
        slushFlicksQueries.run {
            transaction {
                deleteTvCollection(collection)
                for ((id, _, position) in collectionModels) {
                    insertReplaceTvCollection(id, collection, position)
                }
            }
        }
    }

    override suspend fun insertNewTvCollection(tvShow: TvCollectionEntity) {
        slushFlicksQueries.insertReplaceTvCollection(tvShow.id, tvShow.collection, tvShow.position)
    }

    override suspend fun softInsertMovie(movies: List<MovieEntity>) {
        slushFlicksQueries.run {
            transaction {
                for (movie in movies) {
                    insertMovieIgnore(
                        movie.id,
                        movie.voteCount,
                        movie.voteAvg,
                        movie.title,
                        movie.releaseData,
                        movie.backdropPath,
                        movie.overview,
                        movie.posterPath,
                        movie.status,
                        movie.tagline,
                        movie.video,
                        movie.popularity,
                        movie.budget,
                        movie.revenue,
                        movie.runtime,
                        movie.genres,
                        movie.casts
                    )
                }
            }
        }
    }

    override suspend fun softInsertTv(tvShows: List<TvShowEntity>) {
        slushFlicksQueries.run {
            for (tvShow in tvShows) {
                insertTvShowIgnore(
                    tvShow.id,
                    tvShow.voteCount,
                    tvShow.voteAvg,
                    tvShow.title,
                    tvShow.releaseData,
                    tvShow.backdropPath,
                    tvShow.overview,
                    tvShow.posterPath,
                    tvShow.status,
                    tvShow.video,
                    tvShow.popularity,
                    tvShow.runtime,
                    tvShow.directors,
                    tvShow.numOfEpisode,
                    tvShow.numOfSeason,
                    tvShow.genres,
                    tvShow.casts,
                    tvShow.nextEpisode,
                    tvShow.lastEpisode,
                    tvShow.seasons
                )
            }
        }
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionEntity>) {
        collectionModels.forEach {
            slushFlicksQueries.insertReplaceMovieCollection(it.id, it.collection, it.position)
        }
    }

    override suspend fun addTvCollection(collectionModels: List<TvCollectionEntity>) {
        collectionModels.forEach {
            slushFlicksQueries.insertReplaceTvCollection(it.id, it.collection, it.position)
        }
    }

    override suspend fun getMovieDetails(movieId: Long): MovieEntity? {
        return slushFlicksQueries.selectMovie(movieId).executeAsOneOrNull()
    }

    override suspend fun insertMovieDetails(movie: MovieEntity) {
        slushFlicksQueries.insertMovieReplace(
            id = movie.id,
            voteCount = movie.voteCount,
            voteAvg = movie.voteAvg,
            title = movie.title,
            releaseData = movie.releaseData,
            backdropPath = movie.backdropPath,
            overview = movie.overview,
            posterPath = movie.posterPath,
            status = movie.status,
            tagline = movie.tagline,
            video = movie.video,
            popularity = movie.popularity,
            budget = movie.budget,
            revenue = movie.revenue,
            runtime = movie.runtime,
            genres = movie.genres,
            casts = movie.casts
        )
    }

    override suspend fun updateMovieDetails(model: MovieEntity) {
        slushFlicksQueries.upsertMovie(
            voteCount = model.voteCount,
            voteAvg = model.voteAvg,
            releaseData = model.releaseData,
            popularity = model.popularity,
            genres = model.genres,
            budget = model.budget,
            revenue = model.revenue,
            runtime = model.runtime,
            status = model.status,
            tagline = model.tagline,
            id = model.id,
            title = model.title,
            backdropPath = model.backdropPath,
            posterPath = model.posterPath,
            overview = model.overview,
            video = model.video,
            casts = model.casts
        )
    }

    override suspend fun updateMovieDetails(videoKey: String, movieId: Long) {
        slushFlicksQueries.updateMovieVideo(key = videoKey, movieId = movieId)
    }

    override suspend fun updateMovieDetails(casts: List<CastColumn>, movieId: Long) {
        slushFlicksQueries.updateMovieCasts(cast = casts, movieId = movieId)
    }

    override suspend fun insertTvShowDetails(tvShow: TvShowEntity) {
        slushFlicksQueries.insertTvShowReplace(
            id = tvShow.id,
            voteCount = tvShow.voteCount,
            voteAvg = tvShow.voteAvg,
            title = tvShow.title,
            releaseData = tvShow.releaseData,
            backdropPath = tvShow.backdropPath,
            overview = tvShow.overview,
            posterPath = tvShow.posterPath,
            status = tvShow.status,
            video = tvShow.video,
            popularity = tvShow.popularity,
            runtime = tvShow.runtime,
            directors = tvShow.directors,
            numOfEpisode = tvShow.numOfEpisode,
            numOfSeason = tvShow.numOfSeason,
            genres = tvShow.genres,
            casts = tvShow.casts,
            nextEpisode = tvShow.nextEpisode,
            lastEpisode = tvShow.lastEpisode,
            seasons = tvShow.seasons
        )
    }

    override suspend fun getTvShowDetails(tvShowId: Long): TvShowEntity? {
        return slushFlicksQueries.selectTvShow(tvShowId).executeAsOneOrNull()
    }

    override suspend fun updateTvDetails(tvShow: TvShowEntity) {
        slushFlicksQueries.upsertTvShow(
            id = tvShow.id,
            voteCount = tvShow.voteCount,
            voteAvg = tvShow.voteAvg,
            title = tvShow.title,
            releaseData = tvShow.releaseData,
            backdropPath = tvShow.backdropPath,
            overview = tvShow.overview,
            posterPath = tvShow.posterPath,
            status = tvShow.status,
            video = tvShow.video,
            popularity = tvShow.popularity,
            runtime = tvShow.runtime,
            directors = tvShow.directors,
            numOfEpisode = tvShow.numOfEpisode,
            numOfSeason = tvShow.numOfSeason,
            genres = tvShow.genres,
            casts = tvShow.casts,
            nextEpisode = tvShow.nextEpisode,
            lastEpisode = tvShow.lastEpisode,
            seasons = tvShow.seasons
        )
    }

    override suspend fun updateTvDetails(casts: List<CastColumn>, tvShowId: Long) {
        slushFlicksQueries.updateTvShowCasts(cast = casts, tvShowId = tvShowId)
    }

    override suspend fun updateTvDetails(videoKey: String, tvShowId: Long) {
        slushFlicksQueries.updateTvShowVideo(key = videoKey, tvShowId = tvShowId)
    }

    override suspend fun getPagingMovies(collection: String, page: Int): List<ShowEntity> {
        val offset = PAGE_SIZE * (page - 1)
        return slushFlicksQueries.selectPagedMovieList(
            collection,
            PAGE_SIZE.toLong(),
            offset.toLong()
        ).executeAsList().map {
            it.toEntity()
        }
    }

    override suspend fun getPagingTvShows(collection: String, page: Int): List<ShowEntity> {
        val offset = PAGE_SIZE * (page - 1)
        return slushFlicksQueries.selectPagedTvShowList(
            collection,
            PAGE_SIZE.toLong(),
            offset.toLong()
        ).executeAsList().map {
            it.toEntity()
        }
    }
}
