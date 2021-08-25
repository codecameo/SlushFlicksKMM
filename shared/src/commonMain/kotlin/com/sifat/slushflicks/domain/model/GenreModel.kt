package com.sifat.slushflicks.domain.model

import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_ID

data class GenreModel(
    val id: Long = INVALID_ID.toLong(),
    val name: String = EMPTY_STRING
)
