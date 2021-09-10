package com.sifat.slushflicks.component

import com.sifat.slushflicks.data.BULLET_SIGN
import com.sifat.slushflicks.data.NA
import com.sifat.slushflicks.domain.model.GenreModel
import java.text.SimpleDateFormat
import java.util.Locale

fun getGenreList(genres: List<GenreModel>) =
    genres.joinToString(separator = " $BULLET_SIGN ") { it.name }

fun formatReleaseDate(releaseDate: String?): String {
    return releaseDate?.let { dateStr ->
        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStr)
            date?.let {
                val finalDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(date)
                finalDate
            } ?: NA
        } catch (ex: Exception) {
            NA
        }
    } ?: NA
}
