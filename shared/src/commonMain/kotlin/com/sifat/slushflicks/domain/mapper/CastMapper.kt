package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.Constants.DEFAULT_INT
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID_LONG
import com.sifat.slushflicks.data.cache.column.CastColumn
import com.sifat.slushflicks.domain.model.CastModel

fun CastColumn.toModel() = CastModel(
    castId = castId ?: INVALID_ID_LONG,
    character = character ?: EMPTY_STRING,
    name = name ?: EMPTY_STRING,
    order = order ?: DEFAULT_INT,
    profileImage = profileImage ?: EMPTY_STRING
)
