package com.example.architecturedemo.data

import java.lang.Exception

// todo ghy 学习 out 的作用
sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}