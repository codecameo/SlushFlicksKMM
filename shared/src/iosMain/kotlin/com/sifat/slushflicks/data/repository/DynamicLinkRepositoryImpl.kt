package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.DynamicLinkParam
import com.sifat.slushflicks.domain.model.DeeplinkWrapper
import com.sifat.slushflicks.domain.repository.DynamicLinkRepository

class DynamicLinkRepositoryImpl : DynamicLinkRepository {
    override suspend fun generateDynamicLink(param: DynamicLinkParam): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getDynamicLink(deeplinkData: DeeplinkWrapper<*>): Pair<String?, Long?>? {
        TODO("Not yet implemented")
    }
}
