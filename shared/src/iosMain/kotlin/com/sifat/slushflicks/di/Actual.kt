package com.sifat.slushflicks.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogLogger
import com.sifat.slushflicks.data.cache.SlushFlickDb
import com.sifat.slushflicks.di.DiConstants.DATABASE_NAME
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun platformModule() = module {
    single<SqlDriver> {
        NativeSqliteDriver(SlushFlickDb.Schema, get(), get(named(name = DATABASE_NAME)))
    }
}

actual fun getLogger(): Logger = NSLogLogger()