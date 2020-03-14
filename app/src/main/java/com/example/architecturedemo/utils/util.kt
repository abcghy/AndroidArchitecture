package com.example.architecturedemo.utils

inline fun <T> guard(t: T?, block: () -> Nothing): T {
    return t ?: block()
}