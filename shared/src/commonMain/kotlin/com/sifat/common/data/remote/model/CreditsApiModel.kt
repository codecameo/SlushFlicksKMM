package com.sifat.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("cast")
    val casts: List<CastApiModel>? = null
)
