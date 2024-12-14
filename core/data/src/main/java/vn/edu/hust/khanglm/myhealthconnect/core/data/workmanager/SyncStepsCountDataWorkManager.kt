package vn.edu.hust.khanglm.myhealthconnect.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasource
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource

@HiltWorker
class SyncStepsCountDataWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthConnectDatasource: HealthConnectDatasource,
    private val stepsCountDatasource: StepsCountDatasource
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val fetchTime = calculateTimeSyncData()
        val syncedStepsData = healthConnectDatasource.fetchStepsCountData(fetchTime.first, fetchTime.second)
        stepsCountDatasource.insertStepsData(syncedStepsData.map { it.toEntity() })
        return Result.success()
    }

}