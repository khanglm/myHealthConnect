package vn.edu.hust.khanglm.myhealthconnect.healthconnect

import android.os.RemoteException
import android.util.Log
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import vn.edu.hust.khanglm.myhealthconnect.common.toInstant
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectBurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectDistance
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectHeartRate
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectStepsCount
import java.io.IOException
import javax.inject.Inject

internal class HealthConnectDatasourceImpl @Inject constructor(
    private val healthConnectClient: HealthConnectClient
) : HealthConnectDatasource {

    override suspend fun fetchStepsCountData(startTime: Long, endTime: Long): List<HealthConnectStepsCount> {
        return try {
            val startTimeInstant = startTime.toInstant()
            val endTimeInstant = endTime.toInstant()
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        startTimeInstant,
                        endTimeInstant
                    )
                )
            )
            response.records.filterNot { it.metadata.id.isEmpty() }.map {
                HealthConnectStepsCount(
                    id = it.metadata.id,
                    stepsCount = it.count,
                    startTime = it.startTime.toEpochMilli(),
                    endTime = it.endTime.toEpochMilli(),
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
    ): List<HealthConnectBurnedCalories> {
        return try {
            val startTimeInstant = startTime.toInstant()
            val endTimeInstant = endTime.toInstant()
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    TotalCaloriesBurnedRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        startTimeInstant,
                        endTimeInstant
                    )
                )
            )
            response.records.map {
                HealthConnectBurnedCalories(
                    id = it.metadata.id,
                    burnedCalories = it.energy.inKilocalories,
                    startTime = it.startTime.toEpochMilli(),
                    endTime = it.endTime.toEpochMilli(),
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

    override suspend fun fetchHeartRateData(startTime: Long, endTime: Long): List<HealthConnectHeartRate> {
        return try {
            val startTimeInstant = startTime.toInstant()
            val endTimeInstant = endTime.toInstant()
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    HeartRateRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        startTimeInstant,
                        endTimeInstant
                    )
                )
            )
            response.records.filterNot { it.metadata.id.isEmpty() }.map {
                HealthConnectHeartRate(
                    id = it.metadata.id,
                    heartRate = it.samples.sumOf { sample -> sample.beatsPerMinute.toDouble() }/it.samples.size,
                    startTime = it.startTime.toEpochMilli(),
                    endTime = it.endTime.toEpochMilli(),
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

    override suspend fun fetchDistanceData(startTime: Long, endTime: Long) : List<HealthConnectDistance> {
        return try {
            val startTimeInstant = startTime.toInstant()
            val endTimeInstant = endTime.toInstant()
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    DistanceRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(
                        startTimeInstant,
                        endTimeInstant
                    )
                )
            )
            response.records.filterNot { it.metadata.id.isEmpty() }.map {
                HealthConnectDistance(
                    id = it.metadata.id,
                    distance = it.distance.inKilometers,
                    startTime = it.startTime.toEpochMilli(),
                    endTime = it.endTime.toEpochMilli(),
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