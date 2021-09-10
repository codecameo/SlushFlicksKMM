package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.domain.model.CastModel
import com.sifat.slushflicks.domain.model.MovieModel
import com.sifat.slushflicks.domain.utils.getBackdropImage
import com.sifat.slushflicks.domain.utils.getPosterImage

fun MovieEntity.toModel(videoKey: String? = null, movieCasts: List<CastModel>? = null) = MovieModel(
    id = id,
    voteAvg = voteAvg,
    overview = overview,
    voteCount = voteCount,
    backdropPath = getBackdropImage(backdropPath),
    title = title,
    genres = genres.map { it.toModel() },
    releaseData = releaseData,
    posterPath = getPosterImage(posterPath),
    popularity = popularity,
    budget = budget,
    revenue = revenue,
    tagline = tagline,
    status = status,
    runtime = runtime,
    video = videoKey ?: video,
    casts = movieCasts ?: casts.map { it.toModel() }
)
