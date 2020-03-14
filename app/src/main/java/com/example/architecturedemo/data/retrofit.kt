package com.example.architecturedemo.data

import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

object RetrofitInstance {
    private val instance: Retrofit by lazy {
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

        val zhihuGson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, object: JsonDeserializer<Date> {
                val sdf = SimpleDateFormat("yyyyMMdd")
                override fun deserialize(
                    json: JsonElement,
                    typeOfT: Type,
                    context: JsonDeserializationContext
                ): Date {
                    return sdf.parse(json.asString)
                }
            })
            .create()

        Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create(zhihuGson))
            .client(okhttpClient)
            .build()
    }

    val zhihuService by lazy {
        instance.create(ZhihuService::class.java)
    }
}