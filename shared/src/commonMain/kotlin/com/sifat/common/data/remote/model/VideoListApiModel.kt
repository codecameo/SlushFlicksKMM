package com.sifat.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoListApiModel(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("results")
    val results: List<VideoApiModel>? = null
)
