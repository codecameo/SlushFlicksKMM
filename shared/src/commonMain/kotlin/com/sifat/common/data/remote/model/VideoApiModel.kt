package com.sifat.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VideoApiModel(
    @SerialName("id")
    val id: String? = null,
    @SerialName("key")
    val key: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("site")
    val site: String? = null
)
