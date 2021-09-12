package com.sifat.slushflicks.component.movie

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState.Loading
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.component.home.CollectionViewState
import com.sifat.slushflicks.data.Constants.EMPTY_STRING
import com.sifat.slushflicks.data.Constants.INVALID_INT
import com.sifat.slushflicks.data.Label.Companion.TRENDING_LABEL
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.usecase.GetMovieListUseCase
import com.sifat.slushflicks.domain.usecase.GetTrendingMovieListUseCase
import com.sifat.slushflicks.domain.usecase.MovieCollectionUseCase
import com.sifat.slushflicks.viewaction.MovieCollectionViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewaction.MovieCollectionViewAction.FetchMovieListViewAction
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.FetchCollectionViewEvent
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.FetchMovieListViewEvent
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.LoadMoreMovieListViewEvent
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.UpdateCollectionViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class MovieViewModel(
    private val movieListUseCase: GetMovieListUseCase,
    private val trendingMovieListUseCase: GetTrendingMovieListUseCase,
    private val collectionUseCase: MovieCollectionUseCase,
    override val viewState: CollectionViewState = CollectionViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<CollectionViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchCollectionViewEvent -> handleCollectionEvent()
            is UpdateCollectionViewEvent -> updateLabel(event.label)
            is FetchMovieListViewEvent -> handleFetchMovieList()
            is LoadMoreMovieListViewEvent -> handleFetchMovieList()
            else -> throwEventNotSupported(event)
        }
    }

    private suspend fun handleFetchMovieList() {
        if (viewState.isLoadingMore || viewState.page == INVALID_INT) return
        viewState.isLoadingMore = true

        fetchMovieList {
            if (viewState.currentSelectedLabel == TRENDING_LABEL) {
                trendingMovieListUseCase.execute(
                    viewState.currentSelectedLabel,
                    viewState.page + 1
                )
            } else {
                movieListUseCase.execute(
                    viewState.currentSelectedLabel,
                    viewState.page + 1
                )
            }
        }
    }

    private suspend fun fetchMovieList(execute: suspend () -> DataState<List<ShowModel>>) {
        mutableViewActionState.value = when (val state = execute()) {
            is DataState.Error -> FetchMovieListViewAction(getErrorState(state))
            is DataState.Success -> {
                viewState.addShowList(state.data ?: emptyList())
                viewState.isLoadingMore = false
                FetchMovieListViewAction(Success(data = viewState.showList.distinctBy { it.id }))
            }
        }
    }

    private suspend fun handleCollectionEvent() {
        if (viewState.collectionItems.isNotEmpty()) {
            viewState.collectionItems.find { it.selected }
                .let { viewState.reset(it?.label ?: EMPTY_STRING) }
            mutableViewActionState.value =
                FetchCollectionViewAction(Success(data = viewState.collectionItems))
            return
        }
        mutableViewActionState.value = FetchCollectionViewAction(Loading())
        getViewState(collectionUseCase.execute()) {
            viewState.initCollectionList(it)
            viewState.collectionItems
        }.let { state ->
            mutableViewActionState.value = FetchCollectionViewAction(state)
        }
    }

    private fun updateLabel(label: String) {
        viewState.updateSelectedLabel(label)
        mutableViewActionState.value =
            FetchCollectionViewAction(Success(viewState.collectionItems))
    }
}
