package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.DEFAULT_LONG
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.remote.model.MovieDetailsApiModel
import com.sifat.slushflicks.domain.model.MovieModel

fun MovieDetailsApiModel.toModel() = MovieModel(
    id = id ?: DEFAULT_LONG,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    overview = overview ?: EMPTY_STRING,
    voteCount = voteCount ?: DEFAULT_INT,
    backdropPath = backdropPath ?: EMPTY_STRING,
    title = title ?: EMPTY_STRING,
    genres = genres?.map { it.toModel() } ?: emptyList(),
    releaseData = releaseDate ?: EMPTY_STRING,
    posterPath = posterPath ?: EMPTY_STRING,
    popularity = popularity ?: DEFAULT_DOUBLE,
    budget = budget ?: DEFAULT_LONG,
    revenue = revenue ?: DEFAULT_LONG,
    tagline = tagline ?: EMPTY_STRING,
    status = status ?: EMPTY_STRING,
    runtime = runtime ?: DEFAULT_INT
)
