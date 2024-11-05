package vn.edu.hust.khanglm.myhealthconnect.healthconnect

import android.os.RemoteException
import android.util.Log
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.BurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.HeartRate
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.StepsCount
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

internal class HealthConnectDatasourceImpl @Inject constructor(
    private val healthConnectClient: HealthConnectClient
) : HealthConnectDatasource {

    override suspend fun fetchStepsCountData(startTime: Long, endTime: Long): List<StepsCount> {
        return try {
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        LocalDateTime.of(2024, 10, 5, 0, 0, 0),
                        LocalDateTime.of(2024, 11, 1, 23, 59, 59)
                    )
                )
            )
            response.records.filterNot { it.metadata.id.isEmpty() }.map {
                StepsCount(
                    id = it.metadata.id,
                    stepsCount = it.count,
                    startTime = it.startTime.toString(),
                    endTime = it.endTime.toString(),
                )
            }
        } catch (e: SecurityException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData SecurityException: ${e.message}", e)
            emptyList()
        } catch (e: IOException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData IOException: ${e.message}", e)
            emptyList()
        } catch (e: RemoteException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData RemoteException: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun fetchBurnedCaloriesData(
        startTime: Long,
        endTime: Long
    ): List<BurnedCalories> {
        return try {
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    ActiveCaloriesBurnedRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        LocalDateTime.of(2024, 10, 5, 0, 0, 0),
                        LocalDateTime.of(2024, 11, 1, 23, 59, 59)
                    )
                )
            )
            response.records.filterNot { it.metadata.id.isEmpty() }.map {
                BurnedCalories(
                    id = it.metadata.id,
                    burnedCalories = it.energy.inCalories,
                    startTime = it.startTime.toString(),
                    endTime = it.endTime.toString(),
                )
            }
        } catch (e: SecurityException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData SecurityException: ${e.message}", e)
            emptyList()
        } catch (e: IOException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData IOException: ${e.message}", e)
            emptyList()
        } catch (e: RemoteException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData RemoteException: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun fetchHeartRateData(startTime: Long, endTime: Long): List<HeartRate> {
        return try {
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    HeartRateRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        LocalDateTime.of(2024, 10, 5, 0, 0, 0),
                        LocalDateTime.of(2024, 11, 1, 23, 59, 59)
                    )
                )
            )
            response.records.filterNot { it.metadata.id.isEmpty() }.map {
                HeartRate(
                    id = it.metadata.id,
                    heartRate = it.samples.sumOf { sample -> sample.beatsPerMinute.toDouble() }/it.samples.size,
                    startTime = it.startTime.toString(),
                    endTime = it.endTime.toString(),
                )
            }
        } catch (e: SecurityException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData SecurityException: ${e.message}", e)
            emptyList()
        } catch (e: IOException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData IOException: ${e.message}", e)
            emptyList()
        } catch (e: RemoteException) {
            Log.e("HealthConnectDatasourceImpl", "fetchStepsCountData RemoteException: ${e.message}", e)
            emptyList()
        }
    }

}