package com.sifat.common.domain.mapper

import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.domain.model.CollectionModel

fun CollectionEntity.toModel() = CollectionModel(
    name = name,
    label = label
)
