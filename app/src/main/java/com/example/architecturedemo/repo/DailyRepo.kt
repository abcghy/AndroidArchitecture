package com.example.architecturedemo.repo

import android.util.Log
import com.example.architecturedemo.data.HistoryRes
import com.example.architecturedemo.data.LatestRes
import com.example.architecturedemo.data.Result
import com.example.architecturedemo.data.database.DailyLocalDataSource
import com.example.architecturedemo.utils.exhaustive
import java.util.*

class DailyRepo(
    val dailyRemoteDataSource: DailyRemoteDataSource,
    val dailyLocalDataSource: DailyLocalDataSource
) {
    suspend fun refresh(): Result<LatestRes> {
        val res = dailyRemoteDataSource.refresh()
        when (res) {
            is Result.Success -> {
                res.data.stories?.also {
                    dailyLocalDataSource.clearAndInsertStories(it)
                }
            }
            is Result.Error -> {
                // todo ghy select from database
                val storyList = dailyLocalDataSource.queryCacheStories()
                Log.e("STORY", "storyList: $storyList")
            }
        }.exhaustive
        return res
    }

    suspend fun loadMore(date: Date): Result<HistoryRes> {
        val res = dailyRemoteDataSource.loadMore(date)

        return res
    }
}