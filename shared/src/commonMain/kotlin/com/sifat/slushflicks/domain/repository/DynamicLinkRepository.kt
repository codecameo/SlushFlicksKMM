package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.DynamicLinkParam
import com.sifat.slushflicks.domain.model.DeeplinkWrapper

interface DynamicLinkRepository {
    suspend fun generateDynamicLink(param: DynamicLinkParam): String?
    suspend fun getDynamicLink(deeplinkData: DeeplinkWrapper<*>): Pair<String?, Long?>?
}
