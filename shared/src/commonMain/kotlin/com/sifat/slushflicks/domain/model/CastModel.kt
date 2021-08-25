package com.sifat.slushflicks.domain.model

data class CastModel(
    val castId: Int,
    val character: String,
    val name: String,
    val order: Int,
    val profileImage: String?
)
