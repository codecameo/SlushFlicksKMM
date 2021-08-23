package com.sifat.slushflicks.data

object Constants {
    const val EMPTY_STRING = ""
    const val INVALID_ID = -1
    const val DEFAULT_INT = 0
    const val INVALID_ID_LONG = -1L
    const val DEFAULT_LONG = 0L
    const val DEFAULT_DOUBLE = 0.0
    const val PAGE_SIZE = 20
    const val TIME_SECOND_MS = 1000L
}

class Label {
    companion object {
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
}
