package com.sifat.slushflicks.component.tvshow

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.component.home.CollectionViewState
import com.sifat.slushflicks.data.Constants.INVALID_INT
import com.sifat.slushflicks.data.Label.Companion.TRENDING_LABEL
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.usecase.GetTrendingTvListUseCase
import com.sifat.slushflicks.domain.usecase.GetTvShowListUseCase
import com.sifat.slushflicks.domain.usecase.TvCollectionUseCase
import com.sifat.slushflicks.viewaction.TvCollectionViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewaction.TvCollectionViewAction.FetchTvListViewAction
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.FetchCollectionViewEvent
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.FetchTvShowListViewEvent
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.LoadMoreTvShowListViewEvent
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.UpdateCollectionViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class TvShowViewModel(
    private val tvShowListUseCase: GetTvShowListUseCase,
    private val trendingTvListUseCase: GetTrendingTvListUseCase,
    private val collectionUseCase: TvCollectionUseCase,
    override val viewState: CollectionViewState = CollectionViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<CollectionViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchCollectionViewEvent -> handleCollectionEvent()
            is UpdateCollectionViewEvent -> updateLabel(event.label)
            is FetchTvShowListViewEvent -> handleFetchMovieList()
            is LoadMoreTvShowListViewEvent -> handleFetchMovieList()
            else -> throwEventNotSupported(event)
        }
    }

    private suspend fun handleFetchMovieList() {
        if (viewState.isLoadingMore || viewState.page == INVALID_INT) return
        viewState.isLoadingMore = true

        fetchMovieList {
            if (viewState.currentSelectedLabel == TRENDING_LABEL) {
                trendingTvListUseCase.execute(
                    viewState.currentSelectedLabel,
                    viewState.page + 1
                )
            } else {
                tvShowListUseCase.execute(
                    viewState.currentSelectedLabel,
                    viewState.page + 1
                )
            }
        }
    }

    private suspend fun fetchMovieList(execute: suspend () -> DataState<List<ShowModel>>) {
        _viewActionState.value = when (val state = execute()) {
            is DataState.Error -> FetchTvListViewAction(getErrorState(state))
            is DataState.Success -> {
                viewState.addShowList(state.data ?: emptyList())
                viewState.isLoadingMore = false
                FetchTvListViewAction(ViewState.Success(data = viewState.showList.distinctBy { it.id }))
            }
        }
    }

    private suspend fun handleCollectionEvent() {
        _viewActionState.value = FetchCollectionViewAction(ViewState.Loading())
        getViewState(collectionUseCase.execute()) {
            viewState.initCollectionList(it)
            viewState.collectionItems
        }.let { state ->
            _viewActionState.value = FetchCollectionViewAction(state)
        }
    }

    private fun updateLabel(label: String) {
        viewState.updateSelectedLabel(label)
        _viewActionState.value =
            FetchCollectionViewAction(ViewState.Success(viewState.collectionItems))
    }
}
