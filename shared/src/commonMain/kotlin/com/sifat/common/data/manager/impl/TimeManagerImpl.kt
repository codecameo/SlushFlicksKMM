package com.sifat.common.data.manager.impl

import com.sifat.common.data.manager.TimeManager
import kotlinx.datetime.Clock

class TimeManagerImpl : TimeManager {
    override fun getCurrentTime() = Clock.System.now().toEpochMilliseconds()
}
