package com.sifat.slushflicks

import android.app.Application
import co.touchlab.kermit.Kermit
import com.sifat.slushflicks.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SlushFlickApplication : Application(), KoinComponent {
    private val logger: Kermit by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@SlushFlickApplication)
            // modules(appModule)
        }

        logger.d { "SlushFlickApplication" }
    }
}
