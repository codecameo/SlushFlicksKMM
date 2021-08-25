package com.sifat.slushflicks.data.manager.impl

import com.sifat.slushflicks.data.manager.TimeManager
import kotlinx.datetime.Clock

class TimeManagerImpl : TimeManager {
    override fun getCurrentTime() = Clock.System.now().toEpochMilliseconds()
}
