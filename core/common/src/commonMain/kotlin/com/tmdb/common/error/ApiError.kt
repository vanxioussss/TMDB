package com.tmdb.common.error

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents various types of API errors that can occur during network operations.
 */
sealed class ApiError(val message: String) {
    data class Unexpected(val msg: String) : ApiError(msg)
    data class Network(val msg: String) : ApiError(msg)
    data class Server(val msg: String, val code: Int) : ApiError(msg)
    data class Client(val msg: String, val code: Int) : ApiError(msg)
}