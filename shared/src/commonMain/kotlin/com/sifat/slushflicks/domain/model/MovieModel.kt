package com.sifat.slushflicks.domain.model

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.DEFAULT_LONG
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_INT

data class MovieModel(
    val id: Long = INVALID_INT.toLong(),
    val voteCount: Int = DEFAULT_INT,
    val voteAvg: Double = DEFAULT_DOUBLE,
    val title: String = EMPTY_STRING,
    val releaseData: String = EMPTY_STRING,
    val backdropPath: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val posterPath: String = EMPTY_STRING,
    val popularity: Double = DEFAULT_DOUBLE,
    val genres: List<GenreModel> = emptyList(),
    val budget: Long = DEFAULT_LONG,
    val revenue: Long = DEFAULT_LONG,
    val runtime: Int = DEFAULT_INT,
    val status: String = EMPTY_STRING,
    val tagline: String = EMPTY_STRING,
    val video: String = EMPTY_STRING,
    val casts: List<CastModel> = emptyList()
)
