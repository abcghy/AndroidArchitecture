package com.example.architecturedemo.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.architecturedemo.data.Story

@Dao
interface StoryDao {
    @Query("SELECT * FROM Story")
    suspend fun selectCacheStories(): List<Story>

    @Insert
    suspend fun insert(stories: List<Story>)

    @Query("DELETE FROM Story")
    suspend fun deleteAll()
}
