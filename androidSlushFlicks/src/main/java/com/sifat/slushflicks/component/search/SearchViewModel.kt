package com.sifat.slushflicks.component.search

import com.sifat.common.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.common.data.Constants.INVALID_INT
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.usecase.MovieSearchUseCase
import com.sifat.common.domain.usecase.RecentMovieUseCase
import com.sifat.common.domain.usecase.RecentTvShowUseCase
import com.sifat.common.domain.usecase.TvShowSearchUseCase
import com.sifat.common.domain.utils.ShowType
import com.sifat.common.domain.utils.ShowType.MOVIE
import com.sifat.common.domain.utils.ShowType.TV_SHOW
import com.sifat.slushflicks.viewaction.SearchViewAction.ShowResultViewAction
import com.sifat.slushflicks.viewaction.SearchViewAction.UpdateShowTypeViewAction
import com.sifat.slushflicks.viewevents.SearchViewEvent.LoadMoreShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.QueryChangeViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.RefreshShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.UpdateShowTypeViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class SearchViewModel(
    private val movieSearchUseCase: MovieSearchUseCase,
    private val tvShowSearchUseCase: TvShowSearchUseCase,
    private val recentMovieUseCase: RecentMovieUseCase,
    private val recentTvShowUseCase: RecentTvShowUseCase,
    override val viewState: SearchViewState = SearchViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<SearchViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is QueryChangeViewEvent -> handleSearch(event.query)
            is LoadMoreShowViewEvent -> handleLoadMore()
            is UpdateShowTypeViewEvent -> handleUpdateShowType(event.showType)
            is RefreshShowViewEvent -> getShow()
            else -> throwEventNotSupported(event = event)
        }
    }

    private fun handleUpdateShowType(showType: ShowType) {
        if (showType == viewState.showType) return
        viewState.updateShowType(showType)
        mutableViewActionState.value = UpdateShowTypeViewAction(showType = showType)
    }

    private suspend fun handleLoadMore() {
        if (viewState.isLoading || viewState.page == INVALID_INT) return
        viewState.isLoading = true
        getShow()
    }

    private suspend fun handleSearch(query: String = viewState.query) {
        if (query.trim() == viewState.query) return
        viewState.updateQuery(query = query)
        getShow()
    }

    private suspend fun getShow() {
        if (viewState.query.isNotEmpty()) {
            performSearch()
        } else {
            getRecentShow()
        }
    }

    private suspend fun getRecentShow() {
        mutableViewActionState.value = ShowResultViewAction(ViewState.Loading())
        val nextPage = viewState.page + 1
        when (viewState.showType) {
            MOVIE -> recentMovieUseCase.execute(page = nextPage)
            TV_SHOW -> recentTvShowUseCase.execute(page = nextPage)
        }.let { state ->
            mutableViewActionState.value = when (state) {
                is DataState.Error -> ShowResultViewAction(getErrorState(state))
                is DataState.Success -> {
                    if (viewState.query.isEmpty()) {
                        viewState.addShowList(state.data ?: emptyList())
                        viewState.isLoading = false
                    }
                    ShowResultViewAction(Success(data = viewState.searchResult.distinctBy { it.id }))
                }
            }
        }
    }

    private suspend fun performSearch() {
        mutableViewActionState.value = ShowResultViewAction(ViewState.Loading())
        val nextPage = viewState.page + 1
        when (viewState.showType) {
            MOVIE -> movieSearchUseCase.execute(query = viewState.query, page = nextPage)
            TV_SHOW -> tvShowSearchUseCase.execute(query = viewState.query, page = nextPage)
        }.let { state ->
            mutableViewActionState.value = when (state) {
                is DataState.Error -> ShowResultViewAction(getErrorState(state))
                is DataState.Success -> {
                    if (state.data?.query == viewState.query && state.data?.page == nextPage) {
                        viewState.addShowList(state.data?.result ?: emptyList())
                        viewState.isLoading = false
                    }
                    ShowResultViewAction(Success(data = viewState.searchResult.distinctBy { it.id }))
                }
            }
        }
    }
}
