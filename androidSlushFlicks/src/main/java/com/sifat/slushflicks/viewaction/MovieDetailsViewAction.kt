package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.common.domain.model.MovieModel
import com.sifat.common.domain.model.ReviewModel
import com.sifat.common.domain.model.ShowModel

sealed class MovieDetailsViewAction : ViewAction() {
    class FetchMovieDetailsViewAction(val viewState: ViewState<MovieModel>) :
        MovieDetailsViewAction()

    class FetchSimilarMovieViewAction(val viewState: ViewState<List<ShowModel>>) :
        MovieDetailsViewAction()

    class FetchRecommendedMovieViewAction(val viewState: ViewState<List<ShowModel>>) :
        MovieDetailsViewAction()

    class FetchReviewViewAction(val viewState: ViewState<List<ReviewModel>>) :
        MovieCollectionViewAction()

    class ShareViewAction(val viewState: ViewState<String>) :
        MovieCollectionViewAction()
}
