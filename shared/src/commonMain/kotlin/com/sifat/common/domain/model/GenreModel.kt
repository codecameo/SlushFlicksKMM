package com.sifat.common.domain.model

import com.sifat.common.data.Constants.EMPTY_STRING
import com.sifat.common.data.Constants.INVALID_INT

data class GenreModel(
    val id: Long = INVALID_INT.toLong(),
    val name: String = EMPTY_STRING
)
