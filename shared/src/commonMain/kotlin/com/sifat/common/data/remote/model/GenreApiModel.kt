package com.sifat.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null
)
