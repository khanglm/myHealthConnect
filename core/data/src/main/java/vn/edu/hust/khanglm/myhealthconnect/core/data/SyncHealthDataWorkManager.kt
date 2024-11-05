package vn.edu.hust.khanglm.myhealthconnect.core.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import vn.edu.hust.khanglm.core.database.datasource.BurnedCaloriesDatasource
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasource
import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasource
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource
import javax.inject.Inject

class SyncHealthDataWorkManager @Inject constructor(
    @ApplicationContext context: Context,
    params: WorkerParameters,
    private val healthConnectDatasource: HealthConnectDatasource,
    private val stepsCountDatasource: StepsCountDatasource,
    private val heartRateDatasource: HeartRateDatasource,
    private val burnedCaloriesDatasource: BurnedCaloriesDatasource,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        coroutineScope {
            val syncStepsCountJob = launch {
                val syncedStepsData = healthConnectDatasource.fetchStepsCountData(0L, 0L)
                stepsCountDatasource.insertStepsData(syncedStepsData.map { it.toEntity() })
            }
            val syncHeartRateJob = launch {
                val syncedHeartRateData = healthConnectDatasource.fetchHeartRateData(0L, 0L)
                heartRateDatasource.insertHeartRateRecord(syncedHeartRateData.map { it.toEntity() })
            }
            val syncBurnedCaloriesJob = launch {
                val syncedHeartRateData = healthConnectDatasource.fetchBurnedCaloriesData(0L, 0L)
                burnedCaloriesDatasource.insertBurnedCalories(syncedHeartRateData.map { it.toEntity() })
            }
            joinAll(syncHeartRateJob, syncBurnedCaloriesJob, syncStepsCountJob)
        }
        return Result.success()
    }

}