package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.domain.model.ShowModel

fun ShowEntity.toModel() = ShowModel(id, voteAvg, title, backdropPath, overview, genres)
