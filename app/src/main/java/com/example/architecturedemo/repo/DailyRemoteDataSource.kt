package com.example.architecturedemo.repo

import com.example.architecturedemo.utils.safeApiCall
import com.example.architecturedemo.data.HistoryRes
import com.example.architecturedemo.data.LatestRes
import com.example.architecturedemo.data.Result
import com.example.architecturedemo.data.RetrofitInstance
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DailyRemoteDataSource {
    suspend fun refresh(): Result<LatestRes> {
        return safeApiCall({ requestToday() }, "Refresh error")
    }

    private suspend fun requestToday(): Result<LatestRes> {
        val todayResponse = RetrofitInstance.zhihuService.today()
        if (todayResponse.isSuccessful) {
            val data = todayResponse.body()
            if (data != null) {
                return Result.Success(data)
            }
        }
        return Result.Error(
            IOException(
                "IO Exception with refresh api code: ${todayResponse.code()}," +
                        " msg: ${todayResponse.message()}"
            )
        )
    }

    suspend fun loadMore(date: Date): Result<HistoryRes> {
        return safeApiCall(
            { requestHistory(date) },
            "load more error"
        )
    }

    private suspend fun requestHistory(date: Date): Result<HistoryRes> {
        val lastDateStr = SimpleDateFormat("yyyyMMdd").format(date)
        val historyRes = RetrofitInstance.zhihuService.history(lastDateStr)
        if (historyRes.isSuccessful) {
            val data = historyRes.body()
            if (data != null) {
                return Result.Success(data)
            }
        }
        return Result.Error(
            IOException(
                "IO Exception with refresh api code: ${historyRes.code()}," +
                        " msg: ${historyRes.message()}"
            )
        )
    }
}