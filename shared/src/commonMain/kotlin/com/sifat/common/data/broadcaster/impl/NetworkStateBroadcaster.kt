package com.sifat.common.data.broadcaster.impl

import com.sifat.common.AppDispatchers
import com.sifat.common.applicationScope
import com.sifat.common.data.Constants.TIME_SECOND_MS
import com.sifat.common.data.broadcaster.Broadcaster
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * Through this class we can simulate network connectivity
 * related scenario while testing
 * */
class NetworkStateBroadcaster(appDispatchers: AppDispatchers) : Broadcaster<Boolean> {
    private val networkStateObservable = MutableSharedFlow<Boolean>(1)

    init {
        applicationScope.launch(appDispatchers.getIODispatcher()) {
            delay(TIME_SECOND_MS)
            if (networkStateObservable.replayCache.isEmpty()) { // Indicates no Internet connection
                broadcast(false)
            }
        }
    }

    override suspend fun broadcast(value: Boolean) {
        networkStateObservable.emit(value)
    }

    override fun observable(): SharedFlow<Boolean> {
        return networkStateObservable
    }
}
