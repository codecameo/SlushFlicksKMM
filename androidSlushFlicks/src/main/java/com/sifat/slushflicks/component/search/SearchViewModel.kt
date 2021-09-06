package com.sifat.slushflicks.component.search

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.component.search.ShowType.MOVIE
import com.sifat.slushflicks.component.search.ShowType.TV_SHOW
import com.sifat.slushflicks.data.Constants.INVALID_INT
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.usecase.MovieSearchUseCase
import com.sifat.slushflicks.domain.usecase.TvShowSearchUseCase
import com.sifat.slushflicks.viewaction.SearchViewAction.SearchResultViewAction
import com.sifat.slushflicks.viewaction.SearchViewAction.UpdateShowTypeViewAction
import com.sifat.slushflicks.viewevents.SearchViewEvent.LoadMoreShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.RefreshShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.SearchShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.UpdateShowTypeViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class SearchViewModel(
    private val movieSearchUseCase: MovieSearchUseCase,
    private val tvShowSearchUseCase: TvShowSearchUseCase,
    override val viewState: SearchViewState = SearchViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<SearchViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is SearchShowViewEvent -> handleSearch(event.query)
            is LoadMoreShowViewEvent -> handleLoadMore()
            is UpdateShowTypeViewEvent -> handleUpdateShowType(event.showType)
            is RefreshShowViewEvent -> performSearch()
            else -> throwEventNotSupported(event = event)
        }
    }

    private fun handleUpdateShowType(showType: ShowType) {
        if (showType == viewState.showType) return
        viewState.updateShowType(showType)
        _viewActionState.value = UpdateShowTypeViewAction(showType = showType)
    }

    private suspend fun handleLoadMore() {
        if (viewState.isLoading || viewState.page == INVALID_INT) return
        viewState.isLoading = true
        performSearch()
    }

    private suspend fun handleSearch(query: String = viewState.query) {
        if (query.trim() == viewState.query) return
        viewState.updateQuery(query = query)
        performSearch()
    }

    private suspend fun performSearch() {
        _viewActionState.value = SearchResultViewAction(ViewState.Loading())
        val nextPage = viewState.page + 1
        when (viewState.showType) {
            MOVIE -> movieSearchUseCase.execute(query = viewState.query, page = nextPage)
            TV_SHOW -> tvShowSearchUseCase.execute(query = viewState.query, page = nextPage)
        }.let { state ->
            _viewActionState.value = when (state) {
                is DataState.Error -> SearchResultViewAction(getErrorState(state))
                is DataState.Success -> {
                    if (state.data?.query == viewState.query && state.data?.page == nextPage) {
                        viewState.addShowList(state.data?.result ?: emptyList())
                        viewState.isLoading = false
                    }
                    SearchResultViewAction(Success(data = viewState.searchResult.toList()))
                }
            }
        }
    }
}
