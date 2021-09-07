package com.sifat.slushflicks.component.details.movie

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.domain.usecase.MovieDetailsUseCase
import com.sifat.slushflicks.domain.usecase.RecommendedMovieUseCase
import com.sifat.slushflicks.domain.usecase.SimilarMovieUseCase
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchMovieDetailsViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchRecommendedMovieViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchSimilarMovieViewAction
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchMovieDetailsViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchRelatedMovieViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val similarMovieUseCase: SimilarMovieUseCase,
    private val recommendedMovieUseCase: RecommendedMovieUseCase,
    private val movieDetailsUseCase: MovieDetailsUseCase,
    override val viewState: MovieDetailsViewState = MovieDetailsViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<MovieDetailsViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchMovieDetailsViewEvent -> handleMovieDetailsEvent(event.movieId)
            is FetchRelatedMovieViewEvent -> handleRelatedMovie(event.movieId)
            else -> throwEventNotSupported(event = event)
        }
    }

    private suspend fun handleRelatedMovie(movieId: Long) {
        coroutineScope {
            launch {
                _viewActionState.value = FetchRecommendedMovieViewAction(
                    getViewState(recommendedMovieUseCase.execute(movieId, 1))
                )
            }
            launch {
                _viewActionState.value = FetchSimilarMovieViewAction(
                    getViewState(similarMovieUseCase.execute(movieId, 1))
                )
            }
        }
    }

    private suspend fun handleMovieDetailsEvent(movieId: Long) {
        viewState.movieId = movieId
        _viewActionState.value = FetchMovieDetailsViewAction(
            getViewState(movieDetailsUseCase.execute(movieId = movieId))
        )
    }
}
