package com.sifat.slushflicks.data.mapper

import com.sifat.slushflicks.data.Constants
import com.sifat.slushflicks.data.Constants.INVALID_ID
import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.SelectPagedMovieList
import com.sifat.slushflicks.data.cache.SelectPagedTvShowList
import com.sifat.slushflicks.data.cache.column.GenreColumn
import com.sifat.slushflicks.data.cache.model.ShowEntity

fun GenreColumn.toEntity() = GenreEntity(
    id = id ?: INVALID_ID.toLong(),
    name = name ?: Constants.EMPTY_STRING
)

fun SelectPagedMovieList.toEntity() = ShowEntity(
    id = id,
    voteAvg = voteAvg,
    title = title,
    backdropPath = backdropPath,
    overview = overview,
    genres = genres.map { it.toEntity() }
)

fun SelectPagedTvShowList.toEntity() = ShowEntity(
    id = id,
    voteAvg = voteAvg,
    title = title,
    backdropPath = backdropPath,
    overview = overview,
    genres = genres.map { it.toEntity() }
)
