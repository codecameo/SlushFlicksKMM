package com.sifat.slushflicks.data

object Constants {
    const val RATING_FORMAT = "%.1f"
    const val EMPTY_STRING = ""
    const val INVALID_INT = -1
    const val DEFAULT_INT = 0
    const val INVALID_ID_LONG = -1L
    const val DEFAULT_LONG = 0L
    const val DEFAULT_DOUBLE = 0.0
    const val PAGE_SIZE = 20
    const val TIME_SECOND_MS = 1000L
}

object Label {
    const val TRENDING_LABEL = "trending"
    const val POPULAR_LABEL = "popular"
    const val TOP_RATED_LABEL = "top_rated"
    const val UPCOMING_LABEL = "upcoming"
    const val NOW_PLAYING_LABEL = "now_playing"
    const val AIRING_TODAY = "airing_today"
    const val RECENTLY_VISITED_MOVIE = "recent_movie"
    const val RECENTLY_VISITED_TV_SHOW = "recent_tv_show"
    const val SIMILAR_LABEL = "similar"
    const val RECOMMENDATION_LABEL = "recommendations"
    const val DEFAULT_LABEL = TRENDING_LABEL
    const val MOVIE_LABEL = "movie"
    const val TV_LABEL = "tv"
}

object DynamicLinkConstants {
    const val SHOW_ID_PARAM = "show-id"
    const val SHOW_TYPE_PARAM = "show_type"
    const val MOVIE_SHOW_TYPE = "movie"
    const val TV_SERIES_TYPE = "tv_series"
    const val ANDROID_FALL_BACK_URL =
        "https://play.google.com/store/apps/details?id=com.sifat.slushflicks"
    const val IOS_FALL_BACK_URL =
        "https://play.google.com/store/apps/details?id=com.sifat.slushflicks"
    const val SOURCE = "android"
    const val MEDIUM = "android"
    const val CAMPAIGN = "event_share"

    const val QUESTION_MARK = "?"
    const val EQUAL_SIGN = "="
    const val AMPERSAND = "&"
    const val HTTP_PREFIX = "https://"
}

const val YOUTUBE_SITE = "YouTube"
const val VIDEO_TYPE_TRAILER = "Trailer"
const val BULLET_SIGN = "\u2022"
const val NA = "N\\A"
const val SPACE = " "
const val PLAIN_TEXT_TYPE = "text/plain"
