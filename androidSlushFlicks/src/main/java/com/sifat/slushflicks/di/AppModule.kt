package com.sifat.slushflicks.di

import org.koin.dsl.module

fun appModule() = module {
    /*single {
        FirebaseApp.initializeApp(androidContext())
        val fireStore = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        fireStore.firestoreSettings = settings
        fireStore
    }*/
}
