package com.sifat.slushflicks.di

import com.sifat.slushflicks.di.modules.apiModule
import com.sifat.slushflicks.di.modules.appModule
import com.sifat.slushflicks.di.modules.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs = enableNetworkLogs),
            apiModule(),
            appModule(),
            platformModule()
        )
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}
