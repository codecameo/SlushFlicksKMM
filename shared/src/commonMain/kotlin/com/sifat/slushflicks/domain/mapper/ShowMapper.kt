package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.IMAGE_BASE_URL
import com.sifat.slushflicks.data.remote.ImageDimension.W342
import com.sifat.slushflicks.domain.model.ShowModel

fun ShowEntity.toModel() = ShowModel(
    id = id,
    voteAvg = voteAvg,
    title = title,
    backdropPath = "$IMAGE_BASE_URL${W342.dimension}$backdropPath",
    overview = overview,
    genres = genres.map { it.toModel() }
)
