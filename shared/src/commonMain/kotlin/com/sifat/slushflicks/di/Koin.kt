package com.sifat.slushflicks.di

import com.sifat.slushflicks.di.modules.apiModule
import com.sifat.slushflicks.di.modules.appModule
import com.sifat.slushflicks.di.modules.dataModule
import com.sifat.slushflicks.di.modules.networkModule
import com.sifat.slushflicks.di.modules.repositoryModule
import com.sifat.slushflicks.di.modules.useCaseModule
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
            platformModule()
        )
    }

// called by iOS etc
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun initKoin() = initKoin(enableNetworkLogs = false) {}
