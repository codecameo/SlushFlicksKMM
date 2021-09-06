package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.component.search.ShowType
import com.sifat.slushflicks.domain.model.ShowModel

sealed class SearchViewAction : ViewAction() {
    class SearchResultViewAction(val viewState: ViewState<List<ShowModel>>) : SearchViewAction()
    class UpdateShowTypeViewAction(val showType: ShowType) : SearchViewAction()
}
