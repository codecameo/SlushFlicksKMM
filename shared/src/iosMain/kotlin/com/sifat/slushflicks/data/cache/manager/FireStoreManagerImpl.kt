package com.sifat.slushflicks.data.cache.manager

import com.sifat.slushflicks.data.cache.model.CollectionEntity
import com.sifat.slushflicks.data.state.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class FireStoreManagerImpl() : FireStoreManager {
    private val movieCollection = listOf(
        CollectionEntity(label = "trending", name = "Trending"),
        CollectionEntity(label = "now_playing", name = "Now Playing"),
        CollectionEntity(label = "upcoming", name = "Upcoming"),
        CollectionEntity(label = "popular", name = "Popular"),
        CollectionEntity(label = "top_rated", name = "Top Rated")
    )

    private val tvCollection = listOf(
        CollectionEntity(label = "trending", name = "Trending"),
        CollectionEntity(label = "airing_today", name = "Airing Today"),
        CollectionEntity(label = "popular", name = "Popular"),
        CollectionEntity(label = "top_rated", name = "Top Rated")
    )

    override suspend fun getMovieCollections() = DataState.Success(movieCollection)

    override suspend fun getTvCollections() = DataState.Success(tvCollection)
}
