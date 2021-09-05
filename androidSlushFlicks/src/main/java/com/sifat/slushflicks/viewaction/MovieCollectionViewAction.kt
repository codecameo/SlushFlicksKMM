package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.component.home.model.CollectionListModel
import com.sifat.slushflicks.domain.model.ShowModel

sealed class MovieCollectionViewAction : ViewAction() {
    class FetchCollectionViewAction(val viewState: ViewState<List<CollectionListModel>>) :
        MovieCollectionViewAction()

    class FetchMovieListViewAction(val viewState: ViewState<List<ShowModel>>) :
        MovieCollectionViewAction()
}
