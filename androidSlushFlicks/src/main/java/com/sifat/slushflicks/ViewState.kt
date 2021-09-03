package com.sifat.slushflicks

import com.sifat.slushflicks.data.Constants.EMPTY_STRING

sealed class ViewState<Data> {
    class Loading<Data>(
        val data: Data? = null
    ) : ViewState<Data>()

    class Success<Data>(
        val data: Data? = null,
        val message: String? = EMPTY_STRING
    ) : ViewState<Data>()

    class Error<Data>(
        val data: Data? = null,
        val errorMessage: String? = null,
        val errorCode: Int? = null
    ) : ViewState<Data>()
}
