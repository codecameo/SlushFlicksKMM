package com.sifat.slushflicks.data.cache.manager

import com.sifat.slushflicks.data.cache.GenreEntity

interface SessionDataManager {

    fun addGenre(id: Long, name: String)

    fun addGenre(genres: List<GenreEntity>)

    fun getGenre(id: Long): String?

    fun initGenres(genres: List<GenreEntity>?)

    fun getGenres(): Map<Long, String>
}
