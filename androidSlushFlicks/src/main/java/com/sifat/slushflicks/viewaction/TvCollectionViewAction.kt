package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.component.home.model.CollectionListModel

sealed class TvCollectionViewAction : ViewAction() {
    class FetchCollectionViewAction(val viewState: ViewState<List<CollectionListModel>>) :
        TvCollectionViewAction()
}
