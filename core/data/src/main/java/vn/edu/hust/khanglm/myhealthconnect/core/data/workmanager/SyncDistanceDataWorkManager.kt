package vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import vn.edu.hust.khanglm.core.database.datasource.DistanceDatasource
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource

@HiltWorker
internal class SyncDistanceDataWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthConnectDatasource: HealthConnectDatasource,
    private val distanceCaloriesDatasource: DistanceDatasource
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val fetchTime = calculateTimeSyncData()
        val syncedDistanceData = healthConnectDatasource.fetchDistanceData(fetchTime.first, fetchTime.second)
        distanceCaloriesDatasource.insertDistanceData(syncedDistanceData.map { it.toEntity() })

        return Result.success()
    }
}