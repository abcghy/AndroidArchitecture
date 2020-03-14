package com.example.architecturedemo.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ZhihuService {
    @GET("api/4/news/latest")
    suspend fun today(): Response<LatestRes>

    @GET("api/4/news/before/{date}")
    suspend fun history(@Path("date") date: String): Response<HistoryRes>
}