package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.domain.model.CastModel
import com.sifat.slushflicks.domain.model.MovieModel

fun MovieEntity.toModel(videoKey: String? = null, movieCasts: List<CastModel>? = null) = MovieModel(
    id = id,
    voteAvg = voteAvg,
    overview = overview,
    voteCount = voteCount,
    backdropPath = backdropPath,
    title = title,
    genres = genres.map { it.toModel() },
    releaseData = releaseData,
    posterPath = posterPath,
    popularity = popularity,
    budget = budget,
    revenue = revenue,
    tagline = tagline,
    status = status,
    runtime = runtime,
    video = videoKey ?: video,
    casts = movieCasts ?: casts.map { it.toModel() }
)
