package com.sifat.slushflicks

import kotlinx.coroutines.Dispatchers

class AppDispatchersImpl : com.sifat.common.AppDispatchers {
    override fun getIODispatcher() = Dispatchers.Main
    override fun getDefaultDispatcher() = Dispatchers.Default
    override fun getMainDispatcher() = Dispatchers.Main
}
