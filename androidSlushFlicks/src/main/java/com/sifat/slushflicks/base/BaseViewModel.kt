package com.sifat.slushflicks.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.viewaction.InitAction
import com.sifat.slushflicks.viewaction.ViewAction
import com.sifat.slushflicks.viewevents.InitEvent
import com.sifat.slushflicks.viewevents.ViewEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

abstract class BaseViewModel<VS>(
    private val dispatchers: AppDispatchers
) : ViewModel() {

    protected val _viewActionState = MutableStateFlow<ViewAction>(InitAction)
    val viewActionState: StateFlow<ViewAction> = _viewActionState
    val viewEventState = MutableStateFlow<ViewEvent>(InitEvent)

    init {
        viewEventState
            .onEach {
                onReceiveViewEvent(it)
            }.launchIn(viewModelScope)
    }

    protected suspend fun postAction(viewAction: ViewAction) {
        withContext(dispatchers.getMainDispatcher()) {
            _viewActionState.value = viewAction
        }
    }

    protected inline fun <Data, reified ViewData> getErrorState(
        errorState: DataState.Error<Data>,
        convert: (Data?) -> ViewData? = { errorState.data as? ViewData }
    ): ViewState<ViewData> {
        return ViewState.Error(
            errorCode = errorState.statusCode,
            data = convert(errorState.data),
            errorMessage = errorState.errorMessage
        )
    }

    protected inline fun <Data, reified ViewData> getSuccessState(
        state: DataState.Success<Data>,
        convert: (Data?) -> ViewData? = { state.data as? ViewData }
    ): ViewState<ViewData> {
        return state.run { ViewState.Success(data = convert(data), message = message) }
    }

    protected inline fun <Data, reified ViewData> getViewState(
        state: DataState<Data>,
        errorConvert: (Data?) -> ViewData? = { (state as? DataState.Error)?.data as? ViewData },
        successConvert: (Data?) -> ViewData? = { (state as? DataState.Success)?.data as? ViewData }
    ): ViewState<ViewData> {
        return when (state) {
            is DataState.Success -> getSuccessState(state, successConvert)
            is DataState.Error -> getErrorState(state, errorConvert)
        }
    }

    protected fun throwEventNotSupported(event: ViewEvent) {
        throw IllegalStateException("Event $event not supported")
    }

    private suspend fun onReceiveViewEvent(event: ViewEvent) {
        if (event is InitEvent) return
        withContext(dispatchers.getIODispatcher()) {
            handleViewEvent(event)
        }
    }

    protected abstract suspend fun handleViewEvent(event: ViewEvent)

    abstract val viewState: VS
}
