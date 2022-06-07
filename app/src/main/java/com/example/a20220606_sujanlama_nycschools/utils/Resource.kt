package com.example.a20220606_sujanlama_nycschools.utils

data class Resource<out T>(
    val status: STATUS,
    val data: T?,
    val message: String?
) {

    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(data = data, status = STATUS.SUCCESS, message = null)

        fun <T> error(message: String): Resource<T> =
            Resource(status = STATUS.ERROR, message = message, data = null)

        fun <T> loading(): Resource<T> =
            Resource(status = STATUS.LOADING, message = null, data = null)
    }
}

enum class STATUS {
    SUCCESS,
    ERROR,
    LOADING
}