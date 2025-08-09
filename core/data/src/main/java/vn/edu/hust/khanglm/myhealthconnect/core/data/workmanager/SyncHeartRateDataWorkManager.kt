package vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasource
import vn.edu.hust.khanglm.core.datastore.AppDataStoreDataSource
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource

@HiltWorker
class SyncHeartRateDataWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthConnectDatasource: HealthConnectDatasource,
    private val heartRateDatasource: HeartRateDatasource,
    private val dataStoreDataSource: AppDataStoreDataSource
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val fetchTime = calculateTimeSyncData()
        val lastTimeUpdate = dataStoreDataSource.getLastUpdateHeartRate()
        val startTime = lastTimeUpdate.takeIf { it > 0 && it < fetchTime.second } ?: fetchTime.first
        val syncedHeartRateData = healthConnectDatasource.fetchHeartRateData(startTime, fetchTime.second)
        heartRateDatasource.insertHeartRateRecord(syncedHeartRateData.map { it.toEntity() })
        dataStoreDataSource.setLastUpdateHeartRateTimeStamp(fetchTime.second)
        return Result.success()
    }
}