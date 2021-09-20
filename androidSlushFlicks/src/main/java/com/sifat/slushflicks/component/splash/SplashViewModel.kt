package com.sifat.slushflicks.component.splash

import com.sifat.common.AppDispatchers
import com.sifat.common.applicationScope
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.common.data.getAppDispatcher
import com.sifat.common.domain.usecase.GenreUseCase
import com.sifat.slushflicks.viewevents.SplashViewEvent.UpdateGenreEvent
import com.sifat.slushflicks.viewevents.ViewEvent
import kotlinx.coroutines.launch

class SplashViewModel(
    private val genreUseCase: GenreUseCase,
    override val viewState: Unit = Unit,
    appDispatchers: com.sifat.common.AppDispatchers
) : BaseViewModel<Unit>(appDispatchers) {

    override suspend fun handleViewEvent(event: ViewEvent) {
        when (event) {
            is UpdateGenreEvent -> {
                applicationScope.launch(getAppDispatcher().getIODispatcher()) {
                    genreUseCase.execute()
                }
            }
            else -> throwEventNotSupported(event)
        }
    }
}
