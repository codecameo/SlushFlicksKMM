package com.sifat.slushflicks.viewevents

import com.sifat.slushflicks.domain.model.MovieModel

sealed class MovieDetailsViewEvent : ViewEvent() {
    class FetchMovieDetailsViewEvent(val movieId: Long) : MovieDetailsViewEvent()
    class FetchRelatedMovieViewEvent(val movieId: Long) : MovieDetailsViewEvent()
    class FetchReviewViewEvent(val movieId: Long) : MovieDetailsViewEvent()
    class ShareViewEvent(val movie: MovieModel) : MovieDetailsViewEvent()
}
