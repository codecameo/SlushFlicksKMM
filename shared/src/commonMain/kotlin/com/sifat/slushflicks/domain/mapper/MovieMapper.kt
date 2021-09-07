package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.cache.MovieEntity
import com.sifat.slushflicks.data.remote.IMAGE_BASE_URL
import com.sifat.slushflicks.data.remote.ImageDimension.W500
import com.sifat.slushflicks.data.remote.ImageDimension.W780
import com.sifat.slushflicks.domain.model.CastModel
import com.sifat.slushflicks.domain.model.MovieModel

fun MovieEntity.toModel(videoKey: String? = null, movieCasts: List<CastModel>? = null) = MovieModel(
    id = id,
    voteAvg = voteAvg,
    overview = overview,
    voteCount = voteCount,
    backdropPath = "$IMAGE_BASE_URL${W500.dimension}$backdropPath",
    title = title,
    genres = genres.map { it.toModel() },
    releaseData = releaseData,
    posterPath = "$IMAGE_BASE_URL${W780.dimension}$posterPath",
    popularity = popularity,
    budget = budget,
    revenue = revenue,
    tagline = tagline,
    status = status,
    runtime = runtime,
    video = videoKey ?: video,
    casts = movieCasts ?: casts.map { it.toModel() }
)
