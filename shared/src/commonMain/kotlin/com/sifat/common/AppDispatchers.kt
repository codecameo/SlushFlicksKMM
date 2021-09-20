package com.sifat.common

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    fun getIODispatcher(): CoroutineDispatcher
    fun getDefaultDispatcher(): CoroutineDispatcher
    fun getMainDispatcher(): CoroutineDispatcher
}
