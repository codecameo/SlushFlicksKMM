package com.sifat.common.domain.mapper

import com.sifat.common.data.Constants.DEFAULT_INT
import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.Constants.INVALID_ID_LONG
import com.sifat.common.data.cache.column.CastColumn
import com.sifat.common.domain.model.CastModel
import com.sifat.common.domain.utils.getCastImage

fun CastColumn.toModel() = CastModel(
    castId = castId ?: INVALID_ID_LONG,
    character = character ?: EMPTY_STRING,
    name = name ?: EMPTY_STRING,
    order = order ?: DEFAULT_INT,
    profileImage = profileImage?.let { getCastImage(profileImage) }
        ?: EMPTY_STRING
)
