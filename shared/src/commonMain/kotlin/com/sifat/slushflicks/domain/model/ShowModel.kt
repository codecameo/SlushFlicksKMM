package com.sifat.slushflicks.domain.model

import com.sifat.slushflicks.data.cache.GenreEntity

data class ShowModel(
    val id: Long,
    val voteAvg: Double,
    val title: String,
    val backdropPath: String,
    val overview: String,
    val genres: List<GenreEntity>
)
