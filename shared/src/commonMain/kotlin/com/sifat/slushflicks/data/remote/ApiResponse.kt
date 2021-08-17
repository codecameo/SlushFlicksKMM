package com.sifat.slushflicks.data.remote

import com.sifat.slushflicks.data.remote.StatusCode.Companion.INTERNAL_ERROR

sealed class ApiResponse<Data>

data class ApiErrorResponse<Data>(
    val statusCode: Int = INTERNAL_ERROR,
    val errorMessage: String? = null
) : ApiResponse<Data>()

data class ApiSuccessResponse<Data>(
    val data: Data? = null,
    val message: String? = null
) : ApiResponse<Data>()

class ApiEmptyResponse<Data>(
    val statusCode: Int = INTERNAL_ERROR
) : ApiResponse<Data>()
