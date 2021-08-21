package com.sifat.slushflicks.data.cache.model

import com.sifat.slushflicks.data.Constants.INVALID_ID
import com.sifat.slushflicks.data.cache.GenreEntity

data class ShowEntity(
    val id: Long = INVALID_ID.toLong(),
    val voteAvg: Double,
    val title: String,
    val backdropPath: String,
    val overview: String,
    val genres: List<GenreEntity>
)
