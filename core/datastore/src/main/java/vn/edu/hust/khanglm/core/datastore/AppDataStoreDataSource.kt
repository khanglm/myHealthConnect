package vn.edu.hust.khanglm.core.datastore

interface AppDataStoreDataSource {

    suspend fun setLastUpdateBurnedCaloriesTimeStamp(value: Long): Boolean
    suspend fun setLastUpdateStepsTimeStamp(value: Long): Boolean
    suspend fun setLastUpdateHeartRateTimeStamp(value: Long): Boolean
    suspend fun setLastUpdateDistanceTimeStamp(value: Long): Boolean

    suspend fun getLastUpdateBurnedCalories(): Long
    suspend fun getLastUpdateSteps(): Long
    suspend fun getLastUpdateHeartRate(): Long
    suspend fun getLastUpdateDistance(): Long

}