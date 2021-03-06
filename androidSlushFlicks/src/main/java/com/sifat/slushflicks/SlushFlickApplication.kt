package com.sifat.slushflicks

import android.app.Application
import co.touchlab.kermit.Kermit
import com.sifat.slushflicks.di.appModule
import com.sifat.slushflicks.di.initKoin
import com.sifat.slushflicks.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class SlushFlickApplication : Application(), KoinComponent {
    private val logger: Kermit by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin(enableNetworkLogs = BuildConfig.DEBUG) {
            androidLogger()
            androidContext(this@SlushFlickApplication)
            modules(appModule(), viewModelModule())
        }

        logger.d { "SlushFlickApplication" }
    }
}
