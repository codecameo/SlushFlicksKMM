package com.sifat.slushflicks.component.home

import android.net.Uri
import com.sifat.common.AppDispatchers
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.common.domain.model.DeeplinkWrapper
import com.sifat.common.domain.usecase.GetDynamicLinkUseCase
import com.sifat.slushflicks.viewaction.HomeViewAction.FetchDynamicLinkViewAction
import com.sifat.slushflicks.viewevents.HomeViewEvent.FetchDynamicLinkViewEvent
import com.sifat.slushflicks.viewevents.ViewEvent

class HomeViewModel(
    private val getDynamicLinkUseCase: GetDynamicLinkUseCase,
    override val viewState: HomeViewState = HomeViewState(),
    appDispatchers: AppDispatchers
) : BaseViewModel<HomeViewState>(appDispatchers) {
    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is FetchDynamicLinkViewEvent -> handleDeeplinkEvent(event.uri)
            else -> throwEventNotSupported(event = event)
        }
    }

    private suspend fun handleDeeplinkEvent(uri: Uri) {
        postAction(
            FetchDynamicLinkViewAction(
                getViewState(getDynamicLinkUseCase.execute(DeeplinkWrapper(uri)))
            )
        )
    }
}
