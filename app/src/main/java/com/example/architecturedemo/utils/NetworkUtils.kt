package com.example.architecturedemo.utils

import com.example.architecturedemo.data.Result
import java.io.IOException
import java.lang.Exception

suspend fun <T> safeApiCall(call: suspend () -> Result<T>, error: String): Result<T> {
    return try {
        call()
    } catch (exception: Exception) {
        Result.Error(IOException(error))
    }
}