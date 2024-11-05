package vn.edu.hust.khanglm.myhealthconnect.healthconnect

import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.BurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.HeartRate
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.StepsCount

interface HealthConnectDatasource {

    suspend fun fetchStepsCountData(startTime: Long, endTime: Long): List<StepsCount>

    suspend fun fetchBurnedCaloriesData(startTime: Long, endTime: Long): List<BurnedCalories>

    suspend fun fetchHeartRateData(startTime: Long, endTime: Long): List<HeartRate>

}