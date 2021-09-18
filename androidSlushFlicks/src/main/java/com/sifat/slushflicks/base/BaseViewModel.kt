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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

abstract class BaseViewModel<VS>(
    private val dispatchers: AppDispatchers
) : ViewModel() {
    private val eventBufferSize = 5
    protected val mutableViewActionState = MutableStateFlow<ViewAction>(InitAction).also {
        it.buffer(eventBufferSize)
    }
    val viewActionState: Flow<ViewAction> =
        mutableViewActionState.filter { !it.isRead }.onEach { it.isRead = true }
    val viewEventState = MutableStateFlow<ViewEvent>(InitEvent)

    init {
        viewEventState
            .buffer(eventBufferSize)
            .onEach { onReceiveViewEvent(it) }
            .launchIn(viewModelScope)
    }

    protected suspend fun postAction(viewAction: ViewAction) {
        withContext(dispatchers.getMainDispatcher()) {
            mutableViewActionState.value = viewAction
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
        callback: (ViewState<ViewData>) -> Unit = {},
        errorConvert: (Data?) -> ViewData? = { (state as? DataState.Error)?.data as? ViewData },
        successConvert: (Data?) -> ViewData? = { (state as? DataState.Success)?.data as? ViewData }
    ): ViewState<ViewData> {
        return when (state) {
            is DataState.Success -> getSuccessState(state, successConvert)
            is DataState.Error -> getErrorState(state, errorConvert)
        }.also(callback)
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
