package vn.edu.hust.khanglm.myhealthconnect.core.data

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface SyncHealthDataRepository {
    suspend fun syncHealthData()
}

internal class SyncHealthDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SyncHealthDataRepository {
    override suspend fun syncHealthData() {
        val request = PeriodicWorkRequestBuilder<SyncHealthDataWorkManager>(15L, repeatIntervalTimeUnit = TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context)
            .enqueue(request)
    }

}