package com.sifat.common.domain.repository

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.common.data.state.DataState

interface GenreRepository {
    suspend fun updateGenres(): DataState<List<GenreEntity>>
}
