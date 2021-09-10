package com.sifat.slushflicks.viewevents

sealed class TvShowDetailsViewEvent : ViewEvent() {
    class FetchTvShowDetailsViewEvent(val tvShowId: Long) : TvShowDetailsViewEvent()
    class FetchRelatedTvShowViewEvent(val tvShowId: Long) : TvShowDetailsViewEvent()
    class FetchReviewViewEvent(val tvShowId: Long) : TvShowDetailsViewEvent()
}
