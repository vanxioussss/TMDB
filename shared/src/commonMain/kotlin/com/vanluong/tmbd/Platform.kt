package com.vanluong.tmbd

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform