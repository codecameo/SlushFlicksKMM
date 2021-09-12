package com.sifat.slushflicks.utils.ext

fun Float.inRange(max: Float, min: Float): Float {
    return (this - min) / (max - min)
}
