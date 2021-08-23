package com.sifat.slushflicks.data.mapper

import com.sifat.slushflicks.data.Constants.DEFAULT_DOUBLE
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID_LONG
import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.model.ShowEntity
import com.sifat.slushflicks.data.remote.model.GenreApiModel
import com.sifat.slushflicks.data.remote.model.MovieApiModel
import com.sifat.slushflicks.data.remote.model.TvApiModel

fun MovieApiModel.toEntity(genresMap: Map<Long, String>) = ShowEntity(
    id = id ?: INVALID_ID_LONG,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    title = title ?: EMPTY_STRING,
    backdropPath = backdropPath ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    genres = genreIds?.map { GenreEntity(id = it, name = genresMap[it] ?: EMPTY_STRING) }
        ?: emptyList()
)

fun TvApiModel.toEntity(genresMap: Map<Long, String>) = ShowEntity(
    id = id ?: INVALID_ID_LONG,
    voteAvg = voteAverage ?: DEFAULT_DOUBLE,
    title = title ?: EMPTY_STRING,
    backdropPath = backdropPath ?: EMPTY_STRING,
    overview = overview ?: EMPTY_STRING,
    genres = genreIds?.map { GenreEntity(id = it, name = genresMap[it] ?: EMPTY_STRING) }
        ?: emptyList()
)

fun GenreApiModel.toEntity() = GenreEntity(
    id = id ?: INVALID_ID_LONG,
    name = name ?: EMPTY_STRING
)
