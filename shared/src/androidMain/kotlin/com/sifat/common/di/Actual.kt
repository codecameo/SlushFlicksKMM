package com.sifat.common.di

import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Logger
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sifat.slushflicks.data.cache.SlushFlickDb
import com.sifat.common.data.cache.manager.FireStoreManager
import com.sifat.common.data.cache.manager.FireStoreManagerImpl
import com.sifat.common.data.manager.NetworkStateManager
import com.sifat.common.data.manager.NetworkStateManagerImpl
import com.sifat.common.data.repository.DynamicLinkRepositoryImpl
import com.sifat.common.di.DiConstants.DATABASE_NAME
import com.sifat.common.di.DiConstants.NAME_DYNAMIC_BASE_LINK
import com.sifat.common.di.DiConstants.NAME_DYNAMIC_DOMAIN
import com.sifat.common.domain.repository.DynamicLinkRepository
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

    single<NetworkStateManager> { NetworkStateManagerImpl(get(), get()) }

    single {
        val fireStore = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        fireStore.firestoreSettings = settings
        fireStore
    }

    single<DynamicLinkRepository> {
        DynamicLinkRepositoryImpl(
            get(named(name = NAME_DYNAMIC_BASE_LINK)),
            get(named(name = NAME_DYNAMIC_DOMAIN)),
            get()
        )
    }

    single<FireStoreManager> { FireStoreManagerImpl(get()) }

    single { FirebaseDynamicLinks.getInstance() }
}

actual fun getLogger(): Logger = LogcatLogger()
