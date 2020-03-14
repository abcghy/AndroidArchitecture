package com.example.architecturedemo.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromString(string: String): List<String> {
            val listType: Type = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(string, listType)
        }

        @TypeConverter
        @JvmStatic
        fun convertToString(images: List<String>): String {
            return Gson().toJson(images)
        }
    }
}