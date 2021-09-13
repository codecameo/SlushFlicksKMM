package com.sifat.slushflicks.domain.repository

import com.sifat.slushflicks.data.DynamicLinkParam

interface DynamicLinkRepository {
    suspend fun generateDynamicLink(param: DynamicLinkParam): String?
}
