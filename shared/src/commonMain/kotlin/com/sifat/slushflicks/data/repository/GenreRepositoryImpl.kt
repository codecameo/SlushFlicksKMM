package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.cache.GenreEntity
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.GenreRepository

class GenreRepositoryImpl : GenreRepository {
    override fun setGenreList(): DataState<List<GenreEntity>> {
        TODO("Not yet implemented")
    }

    override fun updateGenres(): DataState<List<GenreEntity>> {
        TODO("Not yet implemented")
    }
}
