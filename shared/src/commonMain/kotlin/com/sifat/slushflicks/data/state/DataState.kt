package com.sifat.slushflicks.data.state

import com.sifat.slushflicks.data.remote.StatusCode.Companion.INTERNAL_ERROR

sealed class DataState<Data> {

    class Success<Data>(
        val data: Data? = null,
        val message: String? = null
    ) : DataState<Data>()

    class Error<Data>(
        val statusCode: Int = INTERNAL_ERROR,
        val errorMessage: String? = null,
        val data: Data? = null
    ) : DataState<Data>()
}