package com.sifat.common.data.cache.model

import com.sifat.common.data.Constants.EMPTY_STRING
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionEntity(
    @SerialName("name")
    var name: String = EMPTY_STRING,
    @SerialName("label")
    var label: String = EMPTY_STRING,
)
