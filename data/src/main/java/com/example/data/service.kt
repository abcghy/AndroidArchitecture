package com.example.data

import retrofit2.http.GET

interface ZhihuService {
    @GET("api/4/news/latest")
    suspend fun today(): LatestRes
}