package com.sifat.common.data.remote.api.impl

import com.sifat.common.data.remote.ApiErrorParser
import com.sifat.common.data.remote.ApiErrorResponse
import com.sifat.common.data.remote.ApiResponse
import com.sifat.common.data.remote.ApiSuccessResponse
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.client.statement.request

open class BaseApiImpl(
    private val apiErrorParser: ApiErrorParser
) {

    protected suspend fun <Data> execute(callApi: suspend () -> Data): ApiResponse<Data> {
        return try {
            ApiSuccessResponse(data = callApi())
        } catch (clientRequestEx: ClientRequestException) {
            getApiErrorResponse(clientRequestEx.response)
        } catch (redirectException: RedirectResponseException) {
            getApiErrorResponse(redirectException.response)
        } catch (serverException: ServerResponseException) {
            getApiErrorResponse(serverException.response)
        } catch (responseException: ResponseException) {
            getApiErrorResponse(responseException.response)
        }
    }

    private suspend fun <Data> getApiErrorResponse(httpResponse: HttpResponse): ApiErrorResponse<Data> =
        apiErrorParser.getApiErrorResponse(
            statusCode = httpResponse.status.value,
            method = httpResponse.request.method.value,
            url = httpResponse.request.url.toString(),
            errorResponse = httpResponse.readText()
        )
}
