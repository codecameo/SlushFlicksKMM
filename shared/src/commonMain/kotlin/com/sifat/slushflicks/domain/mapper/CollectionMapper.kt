package com.sifat.slushflicks.domain.mapper

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.domain.model.CollectionModel

fun CollectionEntity.toModel() = CollectionModel(
    name = name,
    label = label
)
