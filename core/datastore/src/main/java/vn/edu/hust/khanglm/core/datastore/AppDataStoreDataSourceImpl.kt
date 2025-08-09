package vn.edu.hust.khanglm.core.datastore

import kotlinx.coroutines.flow.firstOrNull
import vn.edu.hust.khanglm.core.datastore.helper.DataStoreHelper
import javax.inject.Inject

internal class AppDataStoreDataSourceImpl @Inject constructor(
    private val dataStoreHelper: DataStoreHelper
) : AppDataStoreDataSource {
    override suspend fun setLastUpdateBurnedCaloriesTimeStamp(value: Long): Boolean {
        return dataStoreHelper.saveLong(LAST_UPDATE_BURNED_CALORIES_KEY, value)
    }

    override suspend fun setLastUpdateStepsTimeStamp(value: Long): Boolean {
        return dataStoreHelper.saveLong(LAST_UPDATE_STEPS_KEY, value)
    }

    override suspend fun setLastUpdateHeartRateTimeStamp(value: Long): Boolean {
        return dataStoreHelper.saveLong(LAST_UPDATE_HEART_RATE_KEY, value)
    }

    override suspend fun setLastUpdateDistanceTimeStamp(value: Long): Boolean {
        return dataStoreHelper.saveLong(LAST_UPDATE_DISTANCE_KEY, value)
    }

    override suspend fun getLastUpdateBurnedCalories(): Long {
        return dataStoreHelper.observeLongValue(LAST_UPDATE_BURNED_CALORIES_KEY, -1L).firstOrNull() ?: -1L
    }

    override suspend fun getLastUpdateSteps(): Long {
        return dataStoreHelper.observeLongValue(LAST_UPDATE_STEPS_KEY, -1L).firstOrNull() ?: -1L
    }

    override suspend fun getLastUpdateHeartRate(): Long {
        return dataStoreHelper.observeLongValue(LAST_UPDATE_HEART_RATE_KEY, -1L).firstOrNull() ?: -1L
    }

    override suspend fun getLastUpdateDistance(): Long {
        return dataStoreHelper.observeLongValue(LAST_UPDATE_DISTANCE_KEY, -1L).firstOrNull() ?: -1L
    }

    companion object {
        private const val LAST_UPDATE_STEPS_KEY = "LAST_UPDATE_STEPS_KEY"
        private const val LAST_UPDATE_DISTANCE_KEY = "LAST_UPDATE_DISTANCE_KEY"
        private const val LAST_UPDATE_BURNED_CALORIES_KEY = "LAST_UPDATE_BURNED_CALORIES_KEY"
        private const val LAST_UPDATE_HEART_RATE_KEY = "LAST_UPDATE_HEART_RATE_KEY"
    }

}