package com.sifat.common.domain.mapper

import com.sifat.common.data.cache.model.ShowEntity
import com.sifat.common.data.remote.IMAGE_BASE_URL
import com.sifat.common.data.remote.ImageDimension.W342
import com.sifat.common.domain.model.ShowModel

fun ShowEntity.toModel() = ShowModel(
    id = id,
    voteAvg = voteAvg,
    title = title,
    backdropPath = "$IMAGE_BASE_URL${W342.dimension}$backdropPath",
    overview = overview,
    genres = genres.map { it.toModel() }
)
