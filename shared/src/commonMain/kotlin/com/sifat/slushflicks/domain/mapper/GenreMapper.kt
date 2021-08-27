package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.Constants.DEFAULT_LONG
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID_LONG
import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.column.GenreColumn
import com.sifat.slushflicks.data.remote.model.GenreApiModel
import com.sifat.slushflicks.domain.model.GenreModel

fun GenreApiModel.toModel() = GenreModel(
    id = id ?: DEFAULT_LONG,
    name = name ?: EMPTY_STRING
)

fun GenreEntity.toModel() = GenreModel(id = id, name = name)

fun GenreColumn.toModel() = GenreModel(id = id ?: INVALID_ID_LONG, name = name ?: EMPTY_STRING)
