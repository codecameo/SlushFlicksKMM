package com.sifat.slushflicks.data.cache.column

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastColumn(
    @SerialName("cast_id")
    val castId: Int? = null,
    @SerialName("character")
    val character: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("order")
    val order: Int? = null,
    @SerialName("profile_path")
    val profileImage: String? = null
)
