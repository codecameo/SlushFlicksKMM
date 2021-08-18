package com.sifat.slushflicks.di.modules

import co.touchlab.kermit.Kermit
import com.sifat.slushflicks.data.remote.API_KEY
import com.sifat.slushflicks.di.DiConstants
import com.sifat.slushflicks.di.getLogger
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun appModule() = module {
    single { Kermit(getLogger()) }
    single(named(name = DiConstants.API_KEY_NAME)) { API_KEY }
}
