package com.sifat.slushflicks.domain.usecase.impl

import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Error
import com.sifat.slushflicks.data.state.DataState.Success

open class BaseUseCase {
    protected inline fun <Data, reified DomainData> getErrorResponse(
        error: Error<Data>,
        convert: (Data?) -> DomainData? = { (error as? Error)?.data as? DomainData }
    ): Error<DomainData> {
        return Error(
            statusCode = error.statusCode,
            errorMessage = error.errorMessage,
            data = convert(error.data)
        )
    }

    protected inline fun <Data, reified DomainData> getSuccessResponse(
        response: Success<Data>,
        convert: (Data?) -> DomainData? = { response.data as? DomainData }
    ): Success<DomainData> {
        return Success(message = response.message, data = convert(response.data))
    }

    protected inline fun <Data, reified DomainData> getDataState(
        data: DataState<Data>,
        convert: (Data?) -> DomainData? = { (data as? Success)?.data as? DomainData }
    ): DataState<DomainData> {
        return when (data) {
            is Success -> getSuccessResponse(data, convert)
            is Error -> getErrorResponse(data)
        }
    }
}
