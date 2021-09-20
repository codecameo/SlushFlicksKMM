package com.sifat.common.di.modules

import com.sifat.common.data.cache.manager.DatabaseManager
import com.sifat.common.data.cache.manager.LocalDataManager
import com.sifat.common.data.cache.manager.SessionDataManager
import com.sifat.common.data.cache.manager.impl.DatabaseManagerImpl
import com.sifat.common.data.cache.manager.impl.LocalDataManagerImpl
import com.sifat.common.data.cache.manager.impl.SessionDataManagerImpl
import org.koin.dsl.module

fun dataModule() = module {
    single<DatabaseManager> { DatabaseManagerImpl(get()) }
    single<SessionDataManager> { SessionDataManagerImpl() }
    single<LocalDataManager> { LocalDataManagerImpl(get(), get(), get()) }
}
