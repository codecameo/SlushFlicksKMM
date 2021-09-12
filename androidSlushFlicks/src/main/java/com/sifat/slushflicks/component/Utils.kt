package com.sifat.slushflicks.component

import android.content.Context
import com.sifat.slushflicks.R
import com.sifat.slushflicks.data.BULLET_SIGN
import com.sifat.slushflicks.data.NA
import com.sifat.slushflicks.data.remote.StatusCode.Companion.EMPTY_RESPONSE
import com.sifat.slushflicks.data.remote.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.data.remote.StatusCode.Companion.NO_INTERNET_ERROR
import com.sifat.slushflicks.data.remote.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.data.remote.StatusCode.Companion.UNAUTHORIZED
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

fun getErrorMessage(context: Context, errorCode: Int?, errorMessage: String?): String {
    return errorMessage ?: when (errorCode) {
        INTERNAL_ERROR -> context.getString(R.string.error_message)
        NO_INTERNET_ERROR -> context.getString(R.string.error_no_internet)
        RESOURCE_NOT_FOUND -> context.getString(R.string.error_no_data_found)
        UNAUTHORIZED -> context.getString(R.string.error_unauthorized)
        EMPTY_RESPONSE -> context.getString(R.string.error_empty_response)
        else -> context.getString(R.string.error_message)
    }
}
