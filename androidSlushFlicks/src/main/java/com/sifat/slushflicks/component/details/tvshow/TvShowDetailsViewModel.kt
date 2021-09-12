package com.sifat.slushflicks.component.details.tvshow

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.data.Constants.INVALID_INT
import com.sifat.slushflicks.domain.usecase.GetTvReviewUseCase
import com.sifat.slushflicks.domain.usecase.RecommendedTvShowUseCase
import com.sifat.slushflicks.domain.usecase.SimilarTvShowUseCase
import com.sifat.slushflicks.domain.usecase.TvShowDetailsUseCase
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchRecommendedTvShowViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchReviewViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchSimilarTvShowViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchTvShowDetailsViewAction
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchRelatedTvShowViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchReviewViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchTvShowDetailsViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TvShowDetailsViewModel(
    private val similarTvShowUseCase: SimilarTvShowUseCase,
    private val recommendedTvShowUseCase: RecommendedTvShowUseCase,
    private val reviewUseCase: GetTvReviewUseCase,
    private val tvShowDetailsUseCase: TvShowDetailsUseCase,
    override val viewState: TvShowDetailsViewState = TvShowDetailsViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<TvShowDetailsViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchTvShowDetailsViewEvent -> handleTvShowDetailsEvent(event.tvShowId)
            is FetchRelatedTvShowViewEvent -> handleRelatedTvShow(event.tvShowId)
            is FetchReviewViewEvent -> handleReviewEvent(event.tvShowId)
            else -> throwEventNotSupported(event = event)
        }
    }

    private suspend fun handleReviewEvent(tvShowId: Long) {
        if (viewState.reviewPage == INVALID_INT) return
        val nextPage = viewState.reviewPage + 1
        mutableViewActionState.value = FetchReviewViewAction(
            getViewState(
                reviewUseCase.execute(tvShowId = tvShowId, page = nextPage),
                callback = {
                    (it as? ViewState.Success)?.data?.let { list ->
                        viewState.reviewPage =
                            if (list.isEmpty()) INVALID_INT else nextPage
                    }
                }
            )
        )
    }

    private suspend fun handleRelatedTvShow(movieId: Long) {
        coroutineScope {
            launch {
                mutableViewActionState.value = FetchRecommendedTvShowViewAction(
                    getViewState(recommendedTvShowUseCase.execute(movieId, 1))
                )
            }
            launch {
                mutableViewActionState.value = FetchSimilarTvShowViewAction(
                    getViewState(similarTvShowUseCase.execute(movieId, 1))
                )
            }
        }
    }

    private suspend fun handleTvShowDetailsEvent(tvShowId: Long) {
        viewState.tvShowId = tvShowId
        viewState.reviewPage = 0
        coroutineScope {
            tvShowDetailsUseCase.execute(tvShowId = tvShowId)
                .onEach {
                    mutableViewActionState.value = FetchTvShowDetailsViewAction(getViewState(it))
                }
                .launchIn(this)
        }
    }
}
