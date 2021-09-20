package com.sifat.slushflicks.data

import com.sifat.common.AppDispatchers
import com.sifat.slushflicks.AppDispatchersImpl

actual fun getAppDispatcher(): com.sifat.common.AppDispatchers {
    return AppDispatchersImpl()
}
