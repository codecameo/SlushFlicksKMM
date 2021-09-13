package com.sifat.slushflicks.viewevents

import com.sifat.slushflicks.domain.model.TvShowModel

sealed class TvShowDetailsViewEvent : ViewEvent() {
    class FetchTvShowDetailsViewEvent(val tvShowId: Long) : TvShowDetailsViewEvent()
    class FetchRelatedTvShowViewEvent(val tvShowId: Long) : TvShowDetailsViewEvent()
    class FetchReviewViewEvent(val tvShowId: Long) : TvShowDetailsViewEvent()
    class ShareViewEvent(val tvShowModel: TvShowModel) : TvShowDetailsViewEvent()
}
