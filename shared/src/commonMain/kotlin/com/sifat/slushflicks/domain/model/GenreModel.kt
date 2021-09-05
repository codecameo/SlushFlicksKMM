package com.sifat.slushflicks.domain.model

import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_INT

data class GenreModel(
    val id: Long = INVALID_INT.toLong(),
    val name: String = EMPTY_STRING
)
