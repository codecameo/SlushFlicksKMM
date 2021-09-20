package com.sifat.common

import com.sifat.common.AppDispatchers
import kotlinx.coroutines.Dispatchers

class AppDispatchersImpl : com.sifat.common.AppDispatchers {
    override fun getIODispatcher() = Dispatchers.IO
    override fun getDefaultDispatcher() = Dispatchers.Default
    override fun getMainDispatcher() = Dispatchers.Main
}
