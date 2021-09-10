package com.sifat.slushflicks.viewevents

sealed class MovieDetailsViewEvent : ViewEvent() {
    class FetchMovieDetailsViewEvent(val movieId: Long) : MovieDetailsViewEvent()
    class FetchRelatedMovieViewEvent(val movieId: Long) : MovieDetailsViewEvent()
    class FetchReviewViewEvent(val movieId: Long) : MovieDetailsViewEvent()
}
