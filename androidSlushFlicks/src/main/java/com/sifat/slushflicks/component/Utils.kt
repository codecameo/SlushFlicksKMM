package com.sifat.slushflicks.component

import com.sifat.slushflicks.data.BULLET_SIGN
import com.sifat.slushflicks.domain.model.GenreModel

fun getGenreList(genres: List<GenreModel>) =
    genres.joinToString(separator = " $BULLET_SIGN ") { it.name }
