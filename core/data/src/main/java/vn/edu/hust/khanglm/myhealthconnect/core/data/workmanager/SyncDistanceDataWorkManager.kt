package vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import vn.edu.hust.khanglm.core.database.datasource.DistanceDatasource
import vn.edu.hust.khanglm.core.datastore.AppDataStoreDataSource
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource

@HiltWorker
internal class SyncDistanceDataWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthConnectDatasource: HealthConnectDatasource,
    private val distanceCaloriesDatasource: DistanceDatasource,
    private val dataStoreDataSource: AppDataStoreDataSource
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val fetchTime = calculateTimeSyncData()
        val lastTimeUpdate = dataStoreDataSource.getLastUpdateDistance()
        val startTime = lastTimeUpdate.takeIf { it > 0 && it < fetchTime.second } ?: fetchTime.first
        val syncedDistanceData = healthConnectDatasource.fetchDistanceData(startTime, fetchTime.second)
        distanceCaloriesDatasource.insertDistanceData(syncedDistanceData.map { it.toEntity() })
        dataStoreDataSource.setLastUpdateDistanceTimeStamp(fetchTime.second)
        return Result.success()
    }
}