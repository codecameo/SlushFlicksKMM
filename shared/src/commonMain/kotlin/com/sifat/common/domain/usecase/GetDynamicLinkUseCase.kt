package com.sifat.common.domain.usecase

import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.DeepLinkModel
import com.sifat.common.domain.model.DeeplinkWrapper

interface GetDynamicLinkUseCase {
    suspend fun execute(deeplinkWrapper: DeeplinkWrapper<*>): DataState<DeepLinkModel>
}
