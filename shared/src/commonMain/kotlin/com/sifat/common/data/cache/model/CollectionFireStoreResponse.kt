package com.sifat.common.data.cache.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionFireStoreResponse(
    @SerialName("collections")
    var collections: List<CollectionEntity> = emptyList()
)
