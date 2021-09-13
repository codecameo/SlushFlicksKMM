package com.sifat.slushflicks.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
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

fun showTrailer(context: Context, videoId: String): Boolean {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(String.format(YOUTUBE_VIDEO_LINK, videoId))
    )
    intent.`package` = YOUTUBE_PACKAGE_NAME
    val activeApp = context.packageManager.queryIntentActivities(intent, 0)
    if (activeApp.isNotEmpty()) {
        ContextCompat.startActivity(context, intent, null)
    }
    return activeApp.isNotEmpty()
}

const val YOUTUBE_PACKAGE_NAME = "com.google.android.youtube"
const val YOUTUBE_VIDEO_LINK = "https://www.youtube.com/watch?v=%s"
