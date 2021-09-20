package com.sifat.common.data.remote

import com.sifat.common.data.remote.StatusCode.INTERNAL_ERROR

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
