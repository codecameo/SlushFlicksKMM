package com.sifat.slushflicks.domain.usecase

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.DeepLinkModel
import com.sifat.slushflicks.domain.model.DeeplinkWrapper

interface GetDynamicLinkUseCase {
    suspend fun execute(deeplinkWrapper: DeeplinkWrapper<*>): DataState<DeepLinkModel>
}
