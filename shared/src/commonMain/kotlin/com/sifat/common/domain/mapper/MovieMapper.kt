package com.sifat.common.domain.mapper

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.common.domain.model.CastModel
import com.sifat.common.domain.model.MovieModel
import com.sifat.common.domain.utils.getBackdropImage
import com.sifat.common.domain.utils.getPosterImage

fun MovieEntity.toModel(videoKey: String? = null, movieCasts: List<CastModel>? = null) = MovieModel(
    id = id,
    voteAvg = voteAvg,
    overview = overview,
    voteCount = voteCount,
    backdropPath = getBackdropImage(backdropPath),
    title = title,
    genres = genres.map { it.toModel() },
    releaseDate = releaseData,
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
