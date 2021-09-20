package com.sifat.common.data.mapper

import com.sifat.common.data.Constants.DEFAULT_DOUBLE
import com.sifat.common.data.Constants.DEFAULT_INT
import com.sifat.common.data.Constants.DEFAULT_LONG
import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.Constants.INVALID_INT
import com.sifat.common.data.Constants.PAGE_SIZE
import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.MovieCollectionEntity
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.SelectPagedMovieList
import com.sifat.slushflicks.data.cache.SelectPagedTvShowList
import com.sifat.slushflicks.data.cache.TvCollectionEntity
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.common.data.cache.column.GenreColumn
import com.sifat.common.data.cache.model.ShowEntity

fun GenreColumn.toEntity() = GenreEntity(
    id = id ?: INVALID_INT.toLong(),
    name = name ?: EMPTY_STRING
)

fun SelectPagedMovieList.toEntity() = ShowEntity(
    id = id,
    voteAvg = voteAvg,
    title = title,
    backdropPath = backdropPath,
    overview = overview,
    genres = genres.map { it.toEntity() }
)

fun SelectPagedTvShowList.toEntity() = ShowEntity(
    id = id,
    voteAvg = voteAvg,
    title = title,
    backdropPath = backdropPath,
    overview = overview,
    genres = genres.map { it.toEntity() }
)

fun ShowEntity.toMovieCollectionEntity(collection: String, page: Int, index: Int) =
    MovieCollectionEntity(
        collection = collection,
        id = id,
        position = ((page - 1) * PAGE_SIZE) + index
    )

fun ShowEntity.toTvShowCollectionEntity(collection: String, page: Int, index: Int) =
    TvCollectionEntity(
        collection = collection,
        id = id,
        position = ((page - 1) * PAGE_SIZE) + index
    )

fun ShowEntity.toMovieEntity() = MovieEntity(
    id = id,
    voteAvg = voteAvg,
    title = title,
    overview = overview,
    backdropPath = backdropPath,
    genres = genres.map { it.toColumn() },
    releaseData = EMPTY_STRING,
    voteCount = DEFAULT_INT,
    popularity = DEFAULT_DOUBLE,
    posterPath = EMPTY_STRING,
    status = EMPTY_STRING,
    tagline = EMPTY_STRING,
    video = EMPTY_STRING,
    budget = DEFAULT_LONG,
    revenue = DEFAULT_LONG,
    runtime = DEFAULT_INT,
    casts = emptyList()
)

fun ShowEntity.toTvShowEntity() = TvShowEntity(
    id = id,
    voteAvg = voteAvg,
    title = title,
    overview = overview,
    backdropPath = backdropPath,
    genres = genres.map { it.toColumn() },
    releaseData = EMPTY_STRING,
    voteCount = DEFAULT_INT,
    popularity = DEFAULT_DOUBLE,
    posterPath = EMPTY_STRING,
    status = EMPTY_STRING,
    video = EMPTY_STRING,
    runtime = DEFAULT_INT,
    casts = emptyList(),
    numOfEpisode = DEFAULT_INT,
    numOfSeason = DEFAULT_INT,
    lastEpisode = null,
    nextEpisode = null,
    seasons = null,
    directors = EMPTY_STRING
)

fun GenreEntity.toColumn() = GenreColumn(id, name)
