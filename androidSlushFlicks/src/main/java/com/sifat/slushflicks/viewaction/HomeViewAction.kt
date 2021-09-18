package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.domain.model.DeepLinkModel

sealed class HomeViewAction : ViewAction() {
    class FetchDynamicLinkViewAction(val viewState: ViewState<DeepLinkModel>) : HomeViewAction()
}
