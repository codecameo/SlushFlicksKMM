package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.component.home.model.CollectionListModel
import com.sifat.common.domain.model.ShowModel

sealed class TvCollectionViewAction : ViewAction() {
    class FetchCollectionViewAction(val viewState: ViewState<List<CollectionListModel>>) :
        TvCollectionViewAction()

    class FetchTvListViewAction(val viewState: ViewState<List<ShowModel>>) :
        TvCollectionViewAction()
}
