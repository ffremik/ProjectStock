package com.example.projectstock.view.historyscreen.workmanager

import android.content.Context
import android.content.ContextParams
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.projectstock.StockApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "HistoryWorkerManager"
class HistoryWorkerManager(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val daoStock = (applicationContext as StockApplication).getDaoStock()
        return try {
            withContext(Dispatchers.IO){
                daoStock.deleteHistory()
                Log.i(TAG, "Было удаление истории")
            }
            Result.success()
        }catch (throwable: Throwable){
            Log.e(TAG, "Ошибка", throwable)
            Result.failure()
        }
    }

}