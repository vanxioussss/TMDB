package com.tmbd.network.providers

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine

/**
 * Created by van.luong
 * on 16,June,2025
 */
actual fun httpClientEngine(): HttpClientEngine {
    return OkHttpEngine(OkHttpConfig())
}