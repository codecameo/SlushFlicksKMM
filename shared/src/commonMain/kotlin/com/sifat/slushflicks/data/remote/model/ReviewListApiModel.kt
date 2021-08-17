package com.sifat.slushflicks.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewListApiModel(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Long? = null,
    @SerialName("results")
    val results: List<ReviewApiModel>? = null
)
