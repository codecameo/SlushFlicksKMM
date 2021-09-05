package com.sifat.slushflicks.component.splash

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.applicationScope
import com.sifat.slushflicks.base.BaseViewModel
import com.sifat.slushflicks.data.getAppDispatcher
import com.sifat.slushflicks.domain.usecase.GenreUseCase
import com.sifat.slushflicks.viewevents.SplashViewEvent.UpdateGenreEvent
import com.sifat.slushflicks.viewevents.ViewEvent
import kotlinx.coroutines.launch

class SplashViewModel(
    private val genreUseCase: GenreUseCase,
    override val viewState: Unit = Unit,
    appDispatchers: AppDispatchers
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
