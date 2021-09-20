package com.sifat.common.domain.usecase.impl

import com.sifat.common.data.DynamicLinkParam
import com.sifat.common.data.remote.StatusCode.RESOURCE_NOT_FOUND
import com.sifat.common.data.state.DataState
import com.sifat.common.domain.repository.DynamicLinkRepository
import com.sifat.common.domain.usecase.GenerateDynamicLinkUseCase

class GenerateDynamicLinkUseCaseImpl(
    private val dynamicLinkRepository: DynamicLinkRepository
) : GenerateDynamicLinkUseCase {
    override suspend fun execute(param: DynamicLinkParam): DataState<String> {
        return dynamicLinkRepository.generateDynamicLink(param)?.let { shortUrl ->
            DataState.Success(data = shortUrl)
        } ?: DataState.Error(statusCode = RESOURCE_NOT_FOUND)
    }
}
