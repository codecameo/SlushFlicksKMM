package com.sifat.slushflicks.viewaction

import com.sifat.slushflicks.ViewState
import com.sifat.common.domain.model.DeepLinkModel

sealed class HomeViewAction : ViewAction() {
    class FetchDynamicLinkViewAction(val viewState: ViewState<DeepLinkModel>) : HomeViewAction()
}
