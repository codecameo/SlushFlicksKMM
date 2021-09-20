package com.sifat.common.data.cache.model

import com.sifat.common.data.Constants.INVALID_ID_LONG
import com.sifat.slushflicks.data.cache.GenreEntity

data class ShowEntity(
    val id: Long = INVALID_ID_LONG,
    val voteAvg: Double,
    val title: String,
    val backdropPath: String,
    val overview: String,
    val genres: List<GenreEntity>
)
