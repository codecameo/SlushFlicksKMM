package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.DynamicLinkParam
import com.sifat.slushflicks.data.state.DataState

interface GenerateDynamicLinkUseCase {
    suspend fun execute(param: DynamicLinkParam): DataState<String>
}
