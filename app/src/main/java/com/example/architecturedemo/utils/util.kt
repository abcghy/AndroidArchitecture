package com.example.architecturedemo.utils

fun <T> guard(t: T?, block: () -> Nothing): T {
    return t ?: block()
}