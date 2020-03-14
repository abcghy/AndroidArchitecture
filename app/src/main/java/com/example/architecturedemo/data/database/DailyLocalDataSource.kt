package com.example.architecturedemo.data.database

import com.example.architecturedemo.data.Story

class DailyLocalDataSource(private val storyDao: StoryDao) {
    suspend fun queryCacheStories(): List<Story> {
        return storyDao.selectCacheStories()
    }

    suspend fun clearAndInsertStories(stories: List<Story>) {
        storyDao.deleteAll()
        storyDao.insert(stories)
    }
}