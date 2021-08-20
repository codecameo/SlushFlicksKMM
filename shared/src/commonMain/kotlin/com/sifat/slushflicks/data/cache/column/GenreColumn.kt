package com.sifat.slushflicks.data.cache.column

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreColumn(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null
)
