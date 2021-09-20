package com.sifat.common

class Greeting {
    fun greeting(): String {
        return "Hello, ${com.sifat.common.Platform().platform}!"
    }
}
