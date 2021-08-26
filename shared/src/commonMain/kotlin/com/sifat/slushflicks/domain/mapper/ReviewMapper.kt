package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.remote.model.ReviewApiModel
import com.sifat.slushflicks.domain.model.ReviewModel

fun ReviewApiModel.toModel() = ReviewModel(
    author = author ?: EMPTY_STRING,
    content = content ?: EMPTY_STRING,
    id = id ?: EMPTY_STRING
)
