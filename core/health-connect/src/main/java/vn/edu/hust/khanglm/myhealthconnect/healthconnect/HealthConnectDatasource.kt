package vn.edu.hust.khanglm.myhealthconnect.healthconnect

import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectBurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectDistance
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectHeartRate
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectStepsCount

interface HealthConnectDatasource {

    suspend fun fetchStepsCountData(startTime: Long, endTime: Long): List<HealthConnectStepsCount>

    suspend fun fetchBurnedCaloriesData(startTime: Long, endTime: Long): List<HealthConnectBurnedCalories>

    suspend fun fetchHeartRateData(startTime: Long, endTime: Long): List<HealthConnectHeartRate>

    suspend fun fetchDistanceData(startTime: Long, endTime: Long): List<HealthConnectDistance>

}