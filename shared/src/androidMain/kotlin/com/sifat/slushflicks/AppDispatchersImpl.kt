package com.sifat.slushflicks

import kotlinx.coroutines.Dispatchers

class AppDispatchersImpl : AppDispatchers {
    override fun getIODispatcher() = Dispatchers.IO
    override fun getDefaultDispatcher() = Dispatchers.Default
    override fun getMainDispatcher() = Dispatchers.Main
}
