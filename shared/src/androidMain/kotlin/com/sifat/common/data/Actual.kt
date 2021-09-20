package com.sifat.common.data

import com.sifat.common.AppDispatchers
import com.sifat.common.AppDispatchersImpl

actual fun getAppDispatcher(): com.sifat.common.AppDispatchers {
    return AppDispatchersImpl()
}
