package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.component.movie.model.CollectionListModel

sealed class MovieListViewAction : ViewAction() {
    class FetchCollectionViewAction(val viewState: ViewState<List<CollectionListModel>>) :
        MovieListViewAction()
}
