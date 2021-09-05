package com.sifat.slushflicks.component.tvshow

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.component.home.CollectionViewState
import com.sifat.slushflicks.domain.usecase.TvCollectionUseCase
import com.sifat.slushflicks.viewaction.TvCollectionViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.FetchCollectionViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class TvShowViewModel(
    private val collectionUseCase: TvCollectionUseCase,
    override val viewState: CollectionViewState = CollectionViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<CollectionViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchCollectionViewEvent -> handleCollectionEvent()
            else -> throwEventNotSupported(event)
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

    fun updateLabel(label: String) {
        viewState.updateSelectedLabel(label)
        _viewActionState.value =
            FetchCollectionViewAction(ViewState.Success(viewState.collectionItems))
    }
}
