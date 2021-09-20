package com.sifat.common.domain.usecase

import com.sifat.common.data.DynamicLinkParam
import com.sifat.common.data.state.DataState

interface GenerateDynamicLinkUseCase {
    suspend fun execute(param: DynamicLinkParam): DataState<String>
}
