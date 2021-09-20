package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.utils.ShowType

sealed class SearchViewAction : ViewAction() {
    class ShowResultViewAction(val viewState: ViewState<List<ShowModel>>) : SearchViewAction()
    class UpdateShowTypeViewAction(val showType: ShowType) : SearchViewAction()
}
