package com.sifat.slushflicks.component.movie

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Loading
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.domain.usecase.MovieCollectionUseCase
import com.sifat.slushflicks.viewaction.MovieListViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewevents.MovieListViewEvent.FetchCollectionViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class MovieViewModel(
    private val collectionUseCase: MovieCollectionUseCase,
    override val viewState: MovieViewState = MovieViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<MovieViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchCollectionViewEvent -> handleCollectionEvent()
            else -> throwEventNotSupported(event)
        }
    }

    private suspend fun handleCollectionEvent() {
        _viewActionState.value = FetchCollectionViewAction(Loading())
        getViewState(collectionUseCase.getMovieCollection()) {
            viewState.initCollectionList(it)
            viewState.collectionItems
        }.let { state ->
            _viewActionState.value = FetchCollectionViewAction(state)
        }
    }

    fun updateLabel(label: String) {
        viewState.updateSelectedLabel(label)
        _viewActionState.value =
            FetchCollectionViewAction(ViewState.Success(viewState.collectionItems))
    }
}
