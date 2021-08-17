package com.sifat.slushflicks.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreListApiModel(
    @SerialName("genres")
    val genres: List<GenreApiModel>? = null
)
