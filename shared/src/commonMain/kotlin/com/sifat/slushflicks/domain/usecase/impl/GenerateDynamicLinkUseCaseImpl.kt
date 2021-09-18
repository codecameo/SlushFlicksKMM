package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.DynamicLinkParam
import com.sifat.slushflicks.data.remote.StatusCode.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.DynamicLinkRepository
import com.sifat.slushflicks.domain.usecase.GenerateDynamicLinkUseCase

class GenerateDynamicLinkUseCaseImpl(
    private val dynamicLinkRepository: DynamicLinkRepository
) : GenerateDynamicLinkUseCase {
    override suspend fun execute(param: DynamicLinkParam): DataState<String> {
        return dynamicLinkRepository.generateDynamicLink(param)?.let { shortUrl ->
            DataState.Success(data = shortUrl)
        } ?: DataState.Error(statusCode = RESOURCE_NOT_FOUND)
    }
}
