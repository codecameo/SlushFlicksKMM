package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.remote.StatusCode
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.model.DeepLinkModel
import com.sifat.common.domain.model.DeeplinkWrapper
import com.sifat.common.domain.repository.DynamicLinkRepository
import com.sifat.common.domain.usecase.GetDynamicLinkUseCase
import com.sifat.common.domain.utils.ShowType

class GetDynamicLinkUseCaseImpl(
    private val dynamicLinkRepository: DynamicLinkRepository
) : GetDynamicLinkUseCase {
    override suspend fun execute(deeplinkWrapper: DeeplinkWrapper<*>): DataState<DeepLinkModel> {
        return dynamicLinkRepository.getDynamicLink(deeplinkWrapper)?.let {
            if (it.first != null && it.second != null) {
                DataState.Success(
                    DeepLinkModel(
                        showType = ShowType.valueOf(it.first?.uppercase()!!),
                        showId = it.second!!
                    )
                )
            } else {
                DataState.Error(statusCode = StatusCode.EMPTY_RESPONSE)
            }
        } ?: DataState.Error(statusCode = StatusCode.EMPTY_RESPONSE)
    }
}
