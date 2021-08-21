package com.sifat.slushflicks.di

import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Logger
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sifat.slushflicks.data.cache.SlushFlickDb
import com.sifat.slushflicks.data.cache.manager.FireStoreManager
import com.sifat.slushflicks.data.cache.manager.FireStoreManagerImpl
import com.sifat.slushflicks.di.DiConstants.DATABASE_NAME
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
actual fun platformModule() = module {
    single<SqlDriver> {
        AndroidSqliteDriver(SlushFlickDb.Schema, get(), get(named(name = DATABASE_NAME)))
    }

    single {
        val fireStore = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        fireStore.firestoreSettings = settings
        fireStore
    }

    single<FireStoreManager> { FireStoreManagerImpl(get()) }
}

actual fun getLogger(): Logger = LogcatLogger()
