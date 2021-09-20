package com.sifat.slushflicks.component.details.movie

import com.sifat.common.AppDispatchers
import com.sifat.slushflicks.ViewState.Loading
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.common.data.Constants.INVALID_INT
import com.sifat.common.data.DynamicLinkParam
import com.sifat.common.domain.model.MovieModel
import com.sifat.common.domain.usecase.GenerateDynamicLinkUseCase
import com.sifat.common.domain.usecase.GetMovieReviewUseCase
import com.sifat.common.domain.usecase.MovieDetailsUseCase
import com.sifat.common.domain.usecase.RecommendedMovieUseCase
import com.sifat.common.domain.usecase.SimilarMovieUseCase
import com.sifat.common.domain.utils.ShowType.MOVIE
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchMovieDetailsViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchRecommendedMovieViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchReviewViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchSimilarMovieViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.ShareViewAction
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchMovieDetailsViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchRelatedMovieViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchReviewViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.ShareViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val similarMovieUseCase: SimilarMovieUseCase,
    private val recommendedMovieUseCase: RecommendedMovieUseCase,
    private val reviewUseCase: GetMovieReviewUseCase,
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val generateDynamicLinkUseCase: GenerateDynamicLinkUseCase,
    override val viewState: MovieDetailsViewState = MovieDetailsViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<MovieDetailsViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchMovieDetailsViewEvent -> handleMovieDetailsEvent(event.movieId)
            is FetchRelatedMovieViewEvent -> handleRelatedMovie(event.movieId)
            is FetchReviewViewEvent -> handleReviewEvent(event.movieId)
            is ShareViewEvent -> handleShareEvent(event.movie)
            else -> throwEventNotSupported(event = event)
        }
    }

    private suspend fun handleShareEvent(movie: MovieModel) {
        postAction(ShareViewAction(Loading()))
        postAction(
            ShareViewAction(
                getViewState(
                    generateDynamicLinkUseCase.execute(
                        DynamicLinkParam(
                            showId = movie.id,
                            showType = MOVIE.name,
                            showName = movie.title,
                            overview = movie.overview,
                            imageUrl = movie.backdropPath
                        )
                    )
                )
            )
        )
    }

    private suspend fun handleReviewEvent(movieId: Long) {
        if (viewState.reviewPage == INVALID_INT) return
        val nextPage = viewState.reviewPage + 1
        mutableViewActionState.value = FetchReviewViewAction(
            getViewState(
                reviewUseCase.execute(movieId = movieId, page = nextPage),
                callback = {
                    (it as? Success)?.data?.let { list ->
                        viewState.reviewPage = if (list.isEmpty()) INVALID_INT else nextPage
                    }
                }
            )
        )
    }

    private suspend fun handleRelatedMovie(movieId: Long) {
        coroutineScope {
            launch {
                mutableViewActionState.value = FetchRecommendedMovieViewAction(
                    getViewState(recommendedMovieUseCase.execute(movieId, 1))
                )
            }
            launch {
                mutableViewActionState.value = FetchSimilarMovieViewAction(
                    getViewState(similarMovieUseCase.execute(movieId, 1))
                )
            }
        }
    }

    private suspend fun handleMovieDetailsEvent(movieId: Long) {
        viewState.movieId = movieId
        viewState.reviewPage = 0
        coroutineScope {
            movieDetailsUseCase.execute(movieId = movieId)
                .onEach {
                    mutableViewActionState.value = FetchMovieDetailsViewAction(getViewState(it))
                }.launchIn(this)
        }
    }
}
