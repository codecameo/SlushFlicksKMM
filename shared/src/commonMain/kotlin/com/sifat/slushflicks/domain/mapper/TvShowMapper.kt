package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID_LONG
import com.sifat.slushflicks.data.cache.TvShowEntity
import com.sifat.slushflicks.data.cache.column.EpisodeColumn
import com.sifat.slushflicks.data.cache.column.SeasonColumn
import com.sifat.slushflicks.domain.model.CastModel
import com.sifat.slushflicks.domain.model.EpisodeModel
import com.sifat.slushflicks.domain.model.SeasonModel
import com.sifat.slushflicks.domain.model.TvShowModel
import com.sifat.slushflicks.domain.utils.getBackdropImage
import com.sifat.slushflicks.domain.utils.getCastImage
import com.sifat.slushflicks.domain.utils.getEpisodeImage
import com.sifat.slushflicks.domain.utils.getPosterImage

fun TvShowEntity.toModel(videoKey: String? = null, movieCasts: List<CastModel>? = null) =
    TvShowModel(
        id = id,
        backdropPath = getBackdropImage(backdropPath),
        posterPath = getPosterImage(posterPath),
        popularity = popularity,
        voteAvg = voteAvg,
        voteCount = voteCount,
        genres = genres.map { it.toModel() },
        status = status,
        nextEpisode = nextEpisode?.toModel(),
        lastEpisode = lastEpisode?.toModel(),
        seasons = seasons?.map { it.toModel() }?.reversed(),
        numOfSeason = numOfSeason,
        numOfEpisode = numOfEpisode,
        title = title,
        overview = overview,
        releaseDate = releaseData,
        directors = directors,
        runtime = runtime,
        video = videoKey ?: video,
        casts = movieCasts ?: casts.map { it.toModel() }
    )

fun SeasonColumn.toModel() = SeasonModel(
    id = id ?: INVALID_ID_LONG,
    airDate = airDate ?: EMPTY_STRING,
    name = name ?: EMPTY_STRING,
    posterPath = posterPath?.let { getCastImage(it) } ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    seasonNumber = seasonNumber ?: DEFAULT_INT,
    episodeCount = episodeCount ?: DEFAULT_INT
)

fun EpisodeColumn.toModel() = EpisodeModel(
    id = id ?: INVALID_ID_LONG,
    airDate = airDate ?: EMPTY_STRING,
    name = name ?: EMPTY_STRING,
    stillPath = stillPath?.let { getEpisodeImage(it) } ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    voteAvg = voteAvg ?: DEFAULT_DOUBLE,
    seasonNumber = seasonNumber ?: DEFAULT_INT,
    episodeNumber = episodeNumber ?: DEFAULT_INT
)
