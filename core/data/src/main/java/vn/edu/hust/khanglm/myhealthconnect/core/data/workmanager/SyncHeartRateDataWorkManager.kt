package vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasource
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource

@HiltWorker
class SyncHeartRateDataWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthConnectDatasource: HealthConnectDatasource,
    private val heartRateDatasource: HeartRateDatasource,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val fetchTime = calculateTimeSyncData()
        val syncedHeartRateData = healthConnectDatasource.fetchHeartRateData(fetchTime.first, fetchTime.second)
        heartRateDatasource.insertHeartRateRecord(syncedHeartRateData.map { it.toEntity() })
        return Result.success()
    }
}