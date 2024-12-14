package vn.edu.hust.khanglm.myhealthconnect.core.data.repository

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager.SyncBurnedCaloriesDataWorkManager
import vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager.SyncDistanceDataWorkManager
import vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager.SyncHeartRateDataWorkManager
import vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager.SyncStepsCountDataWorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface SyncHealthDataRepository {
    fun startWorkerSyncStepData()
    fun startWorkerSyncBurnedCaloriesData()
    fun startWorkerSyncHearRateData()
    fun startWorkerSyncDistanceData()
}

internal class SyncHealthDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SyncHealthDataRepository {

    override fun startWorkerSyncStepData() {
        val request = PeriodicWorkRequestBuilder<SyncStepsCountDataWorkManager>(
            repeatInterval = TIME_REPEAT_INTERVAL,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .addTag(WORKER_SYNC_STEPS_TAG)
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(WORKER_SYNC_STEPS_TAG, ExistingPeriodicWorkPolicy.KEEP, request)
    }

    override fun startWorkerSyncBurnedCaloriesData() {
        val request = PeriodicWorkRequestBuilder<SyncBurnedCaloriesDataWorkManager>(
            repeatInterval = TIME_REPEAT_INTERVAL,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .addTag(WORKER_CALORIES_TAG)
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(WORKER_CALORIES_TAG, ExistingPeriodicWorkPolicy.KEEP, request)
    }

    override fun startWorkerSyncHearRateData() {
        val request = PeriodicWorkRequestBuilder<SyncHeartRateDataWorkManager>(
            repeatInterval = TIME_REPEAT_INTERVAL,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .addTag(WORKER_HEART_RATE_TAG)
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(WORKER_HEART_RATE_TAG, ExistingPeriodicWorkPolicy.KEEP, request)
    }

    override fun startWorkerSyncDistanceData() {
        val request = PeriodicWorkRequestBuilder<SyncDistanceDataWorkManager>(
            repeatInterval = TIME_REPEAT_INTERVAL,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .addTag(WORKER_DISTANCE_TAG)
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(WORKER_DISTANCE_TAG, ExistingPeriodicWorkPolicy.KEEP, request)
    }

    companion object {
        private const val TIME_REPEAT_INTERVAL = 15L
        const val WORKER_SYNC_STEPS_TAG = "StepsSyncDataWorker"
        const val WORKER_CALORIES_TAG = "BurnedCaloriesSyncDataWorker"
        const val WORKER_HEART_RATE_TAG = "HeartRateSyncDataWorker"
        const val WORKER_DISTANCE_TAG = "DistanceSyncDataWorker"
    }
}