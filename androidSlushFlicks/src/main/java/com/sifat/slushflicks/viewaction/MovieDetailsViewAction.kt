package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.domain.model.MovieModel
import com.sifat.slushflicks.domain.model.ShowModel

sealed class MovieDetailsViewAction : ViewAction() {
    class FetchMovieDetailsViewAction(val viewState: ViewState<MovieModel>) :
        MovieDetailsViewAction()

    class FetchSimilarMovieViewAction(val viewState: ViewState<List<ShowModel>>) :
        MovieDetailsViewAction()

    class FetchRecommendedMovieViewAction(val viewState: ViewState<List<ShowModel>>) :
        MovieDetailsViewAction()
}
