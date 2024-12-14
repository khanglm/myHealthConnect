package vn.edu.hust.khanglm.myhealthconnect.core.data.repository

import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.datasource.BurnedCaloriesDatasource
import vn.edu.hust.khanglm.core.database.datasource.DistanceDatasource
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasource
import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasource
import javax.inject.Inject

interface HealthDataTodayRepository {

    fun observeTotalSteps(startTime: Long, endTime: Long): Flow<Long>

    fun observeTotalBurnedCalories(startTime: Long, endTime: Long): Flow<Double>

    fun observeAvgHeartRate(startTime: Long, endTime: Long): Flow<Double>

    fun observeDistance(startTime: Long, endTime: Long): Flow<Double>

}

internal class HealthDataTodayRepositoryImpl @Inject constructor(
    private val stepsCountDatasource: StepsCountDatasource,
    private val burnedCaloriesDatasource: BurnedCaloriesDatasource,
    private val heartRateDatasource: HeartRateDatasource,
    private val distanceDatasource: DistanceDatasource,
) : HealthDataTodayRepository {

    override fun observeTotalSteps(startTime: Long, endTime: Long): Flow<Long> {
        return stepsCountDatasource.observeTotalStepInTimeRange(startTime, endTime)
    }

    override fun observeTotalBurnedCalories(startTime: Long, endTime: Long): Flow<Double> {
        return burnedCaloriesDatasource.observeBurnedCaloriesInTimeRange(startTime, endTime)
    }

    override fun observeAvgHeartRate(startTime: Long, endTime: Long): Flow<Double> {
        return heartRateDatasource.observeAverageHeartRate(startTime, endTime)
    }

    override fun observeDistance(startTime: Long, endTime: Long): Flow<Double> {
        return distanceDatasource.observeTotalDistanceInTimeRange(startTime, endTime)
    }

}