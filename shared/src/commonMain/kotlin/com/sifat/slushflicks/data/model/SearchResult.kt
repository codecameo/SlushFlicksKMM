package com.sifat.slushflicks.data.model

data class SearchResult<Data>(
    val query: String,
    val page: Int,
    val result: List<Data>
)
