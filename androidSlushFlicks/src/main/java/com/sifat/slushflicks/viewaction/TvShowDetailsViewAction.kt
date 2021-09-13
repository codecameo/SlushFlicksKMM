package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.model.TvShowModel

sealed class TvShowDetailsViewAction : ViewAction() {
    class FetchTvShowDetailsViewAction(val viewState: ViewState<TvShowModel>) :
        TvShowDetailsViewAction()

    class FetchSimilarTvShowViewAction(val viewState: ViewState<List<ShowModel>>) :
        TvShowDetailsViewAction()

    class FetchRecommendedTvShowViewAction(val viewState: ViewState<List<ShowModel>>) :
        TvShowDetailsViewAction()

    class FetchReviewViewAction(val viewState: ViewState<List<ReviewModel>>) :
        TvShowDetailsViewAction()

    class ShareViewAction(val viewState: ViewState<String>) :
        TvShowDetailsViewAction()
}
