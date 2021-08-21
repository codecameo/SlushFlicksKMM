package com.sifat.slushflicks

import android.os.Build.VERSION

actual class Platform actual constructor() {
    actual val platform: String = "Android ${VERSION.SDK_INT}"
}
