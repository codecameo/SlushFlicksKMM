package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.remote.StatusCode
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.model.DeepLinkModel
import com.sifat.slushflicks.domain.model.DeeplinkWrapper
import com.sifat.slushflicks.domain.repository.DynamicLinkRepository
import com.sifat.slushflicks.domain.usecase.GetDynamicLinkUseCase
import com.sifat.slushflicks.domain.utils.ShowType

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
