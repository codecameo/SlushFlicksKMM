package com.sifat.slushflicks.data.cache.manager.impl

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.cache.manager.SessionDataManager

class SessionDataManagerImpl : SessionDataManager {
    private val genreMap = mutableMapOf<Long, String>()

    override fun addGenre(id: Long, name: String) {
        genreMap[id] = name
    }

    override fun addGenre(genres: List<GenreEntity>) {
        for (genre in genres) {
            genreMap[genre.id] = genre.name
        }
    }

    override fun getGenre(id: Long): String? {
        return genreMap[id]
    }

    override fun initGenres(genres: List<GenreEntity>?) {
        if (!genres.isNullOrEmpty()) {
            genreMap.clear()
            for (genre in genres) {
                genreMap[genre.id] = genre.name
            }
        }
    }

    override fun getGenres(): Map<Long, String> {
        return genreMap
    }
}
