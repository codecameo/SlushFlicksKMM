package com.sifat.slushflicks.data.repository

import com.sifat.slushflicks.data.manager.NetworkStateManager
import com.sifat.slushflicks.data.remote.ApiEmptyResponse
import com.sifat.slushflicks.data.remote.ApiErrorResponse
import com.sifat.slushflicks.data.remote.ApiResponse
import com.sifat.slushflicks.data.remote.ApiSuccessResponse
import com.sifat.slushflicks.data.remote.StatusCode.EMPTY_RESPONSE
import com.sifat.slushflicks.data.remote.StatusCode.NO_INTERNET_ERROR
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.data.state.DataState.Error
import com.sifat.slushflicks.data.state.DataState.Success

open class BaseRepository(
    private val networkStateManager: NetworkStateManager
) {
    protected suspend fun <Data> execute(
        fallback: suspend () -> DataState<Data> = { Error(statusCode = NO_INTERNET_ERROR) },
        online: suspend () -> DataState<Data>
    ): DataState<Data> {
        return try {
            if (networkStateManager.isOnline()) {
                try {
                    online()
                } catch (ex: Exception) {
                    fallback()
                }
            } else {
                fallback()
            }
        } catch (ex: Exception) {
            Error(errorMessage = ex.message)
        }
    }

    protected inline fun <ApiData, reified Data> getErrorResponse(
        errorResponse: ApiErrorResponse<ApiData>
    ): Error<Data> {
        return Error(
            statusCode = errorResponse.statusCode,
            errorMessage = errorResponse.errorMessage
        )
    }

    protected inline fun <ApiData, reified Data> getSuccessResponse(
        response: ApiSuccessResponse<ApiData>,
        convert: (ApiData?) -> Data? = { response.data as? Data }
    ): Success<Data> {
        return Success(message = response.message, data = convert(response.data))
    }

    protected inline fun <ApiData, reified Data> getDataState(
        apiResponse: ApiResponse<ApiData>,
        convert: (ApiData?) -> Data? = { (apiResponse as? ApiSuccessResponse)?.data as? Data }
    ): DataState<Data> {
        return when (apiResponse) {
            is ApiSuccessResponse -> getSuccessResponse(apiResponse, convert)
            is ApiErrorResponse -> getErrorResponse(apiResponse)
            is ApiEmptyResponse -> Error(statusCode = EMPTY_RESPONSE)
        }
    }
}
