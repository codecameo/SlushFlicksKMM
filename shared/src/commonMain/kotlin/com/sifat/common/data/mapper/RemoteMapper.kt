package com.sifat.common.data.mapper

import com.sifat.common.data.Constants.DEFAULT_DOUBLE
import com.sifat.common.data.Constants.DEFAULT_INT
import com.sifat.common.data.Constants.DEFAULT_LONG
import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.Constants.INVALID_ID_LONG
import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.common.data.cache.column.CastColumn
import com.sifat.common.data.cache.column.EpisodeColumn
import com.sifat.common.data.cache.column.GenreColumn
import com.sifat.common.data.cache.column.SeasonColumn
import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.getDirectors
import com.sifat.common.data.getRuntime
import com.sifat.common.data.getSeasons
import com.sifat.common.data.remote.model.CastApiModel
import com.sifat.common.data.remote.model.EpisodeApiModel
import com.sifat.common.data.remote.model.GenreApiModel
import com.sifat.common.data.remote.model.MovieApiModel
import com.sifat.common.data.remote.model.MovieDetailsApiModel
import com.sifat.common.data.remote.model.SeasonApiModel
import com.sifat.common.data.remote.model.TvApiModel
import com.sifat.common.data.remote.model.TvShowDetailsApiModel

fun MovieApiModel.toEntity(genresMap: Map<Long, String>) = ShowEntity(
    id = id ?: INVALID_ID_LONG,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    title = title ?: EMPTY_STRING,
    backdropPath = backdropPath ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    genres = genreIds?.map { GenreEntity(id = it, name = genresMap[it] ?: EMPTY_STRING) }
        ?: emptyList()
)

fun TvApiModel.toEntity(genresMap: Map<Long, String>) = ShowEntity(
    id = id ?: INVALID_ID_LONG,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    title = title ?: EMPTY_STRING,
    backdropPath = backdropPath ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    genres = genreIds?.map { GenreEntity(id = it, name = genresMap[it] ?: EMPTY_STRING) }
        ?: emptyList()
)

fun GenreApiModel.toEntity() = GenreEntity(
    id = id ?: INVALID_ID_LONG,
    name = name ?: EMPTY_STRING
)

fun GenreApiModel.toColumn() = GenreColumn(
    id = id ?: INVALID_ID_LONG,
    name = name ?: EMPTY_STRING
)

fun MovieDetailsApiModel.toEntity() = MovieEntity(
    id = id ?: DEFAULT_LONG,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    overview = overview ?: EMPTY_STRING,
    voteCount = voteCount ?: DEFAULT_INT,
    backdropPath = backdropPath ?: EMPTY_STRING,
    title = title ?: EMPTY_STRING,
    genres = genres?.map { it.toColumn() } ?: emptyList(),
    releaseData = releaseDate ?: EMPTY_STRING,
    posterPath = posterPath ?: EMPTY_STRING,
    popularity = popularity ?: DEFAULT_DOUBLE,
    budget = budget ?: DEFAULT_LONG,
    revenue = revenue ?: DEFAULT_LONG,
    tagline = tagline ?: EMPTY_STRING,
    status = status ?: EMPTY_STRING,
    runtime = runtime ?: DEFAULT_INT,
    casts = emptyList(),
    video = EMPTY_STRING
)

fun CastApiModel.toColumn() = CastColumn(
    castId = castId,
    character = character,
    name = name,
    order = order,
    profileImage = profileImage
)

fun TvShowDetailsApiModel.toEntity() = TvShowEntity(
    id = id ?: DEFAULT_LONG,
    backdropPath = backdropPath ?: EMPTY_STRING,
    posterPath = posterPath ?: EMPTY_STRING,
    popularity = popularity ?: DEFAULT_DOUBLE,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    voteCount = voteCount ?: DEFAULT_INT,
    genres = genres?.map { it.toColumn() } ?: emptyList(),
    status = status ?: EMPTY_STRING,
    nextEpisode = nextEpisode?.toColumn(),
    lastEpisode = lastEpisode?.toColumn(),
    seasons = getSeasons(seasons),
    numOfSeason = seasonCount ?: DEFAULT_INT,
    numOfEpisode = episodeCount ?: DEFAULT_INT,
    title = name ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    releaseData = firstAirDate ?: EMPTY_STRING,
    directors = getDirectors(createdBy),
    runtime = getRuntime(episodeRunTime),
    video = EMPTY_STRING,
    casts = emptyList()
)

fun SeasonApiModel.toColumn() = SeasonColumn(
    id = id,
    airDate = airDate ?: EMPTY_STRING,
    name = name,
    posterPath = posterPath ?: EMPTY_STRING,
    overview = overview,
    seasonNumber = seasonNumber,
    episodeCount = episodeCount
)

fun EpisodeApiModel.toColumn() = EpisodeColumn(
    id = id,
    airDate = airDate ?: EMPTY_STRING,
    name = name,
    stillPath = stillPath ?: EMPTY_STRING,
    overview = overview,
    voteAvg = voteAvg,
    seasonNumber = seasonNumber,
    episodeNumber = episodeNumber
)
