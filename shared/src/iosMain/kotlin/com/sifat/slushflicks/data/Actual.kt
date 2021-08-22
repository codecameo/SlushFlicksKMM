package com.sifat.slushflicks.data

import com.sifat.slushflicks.AppDispatchers
import com.sifat.slushflicks.AppDispatchersImpl

actual fun getAppDispatcher(): AppDispatchers {
    return AppDispatchersImpl()
}
