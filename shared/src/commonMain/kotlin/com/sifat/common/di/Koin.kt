package com.sifat.common.di

import com.sifat.common.di.modules.apiModule
import com.sifat.common.di.modules.appModule
import com.sifat.common.di.modules.dataModule
import com.sifat.common.di.modules.networkModule
import com.sifat.common.di.modules.repositoryModule
import com.sifat.common.di.modules.useCaseModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs = enableNetworkLogs),
            apiModule(),
            appModule(),
            dataModule(),
            repositoryModule(),
            useCaseModule(),
            com.sifat.common.di.platformModule()
        )
    }

// called by iOS etc
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun initKoin() = initKoin(enableNetworkLogs = false) {}
