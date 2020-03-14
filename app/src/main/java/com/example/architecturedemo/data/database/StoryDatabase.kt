package com.example.architecturedemo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.architecturedemo.data.Story
import retrofit2.Converter

@Database(entities = [Story::class], version = 1)
@TypeConverters(Converters::class)
abstract class StoryDatabase: RoomDatabase() {
    abstract fun storyDao(): StoryDao

    companion object {
        private const val DATABASE_NAME = "zhihu-daily.db"

        private var INSTANCE: StoryDatabase? = null

        fun getInstance(context: Context): StoryDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): StoryDatabase {
            return Room.databaseBuilder(
                context, StoryDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}