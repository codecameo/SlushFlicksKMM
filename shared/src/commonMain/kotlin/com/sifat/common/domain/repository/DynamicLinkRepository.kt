package com.sifat.common.domain.repository

import com.sifat.common.data.DynamicLinkParam
import com.sifat.common.domain.model.DeeplinkWrapper

interface DynamicLinkRepository {
    suspend fun generateDynamicLink(param: DynamicLinkParam): String?
    suspend fun getDynamicLink(deeplinkData: DeeplinkWrapper<*>): Pair<String?, Long?>?
}
