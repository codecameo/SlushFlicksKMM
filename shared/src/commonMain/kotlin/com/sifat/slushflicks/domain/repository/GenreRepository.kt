package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.state.DataState

interface GenreRepository {
    fun setGenreList(): DataState<List<GenreEntity>>

    fun updateGenres(): DataState<List<GenreEntity>>
}
