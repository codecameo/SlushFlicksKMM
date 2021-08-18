package com.sifat.slushflicks.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogLogger
import org.koin.dsl.module

actual fun platformModule() = module {
    /*single {
        val driver = NativeSqliteDriver(PeopleInSpaceDatabase.Schema, "peopleinspace.db")
        PeopleInSpaceDatabaseWrapper(PeopleInSpaceDatabase(driver))
    }*/
}

actual fun getLogger(): Logger = NSLogLogger()
