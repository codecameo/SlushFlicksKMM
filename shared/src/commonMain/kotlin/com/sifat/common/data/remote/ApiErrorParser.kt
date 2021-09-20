package com.sifat.common.data.remote

import com.sifat.common.data.Constants.EMPTY_STRING
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface ApiErrorParser {
    fun <Data> getApiErrorResponse(
        statusCode: Int,
        url: String?,
        method: String = HttpMethod.Get.value,
        errorResponse: String?
    ): ApiErrorResponse<Data>
}

class ApiErrorParserImpl(private val json: Json) : ApiErrorParser {
    override fun <Data> getApiErrorResponse(
        statusCode: Int,
        url: String?,
        method: String,
        errorResponse: String?
    ): ApiErrorResponse<Data> {
        return try {
            errorResponse?.let {
                ApiErrorResponse(
                    statusCode = statusCode,
                    errorMessage = json.decodeFromString<ErrorResponse>(errorResponse).message
                        ?: EMPTY_STRING
                )
            } ?: ApiErrorResponse()
        } catch (ex: Exception) {
            ApiErrorResponse()
        }
    }
}
