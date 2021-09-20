package com.sifat.common.domain.mapper

import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.remote.model.ReviewApiModel
import com.sifat.common.domain.model.ReviewModel

fun ReviewApiModel.toModel() = ReviewModel(
    author = author ?: EMPTY_STRING,
    content = content ?: EMPTY_STRING,
    id = id ?: EMPTY_STRING
)
