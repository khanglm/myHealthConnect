package vn.edu.hust.khanglm.core.database.datasource

import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.dao.BurnedCaloriesDao
import vn.edu.hust.khanglm.core.database.entities.HealthBurnedCaloriesEntity
import javax.inject.Inject

interface BurnedCaloriesDatasource {
    suspend fun insertBurnedCalories(data: List<HealthBurnedCaloriesEntity>): List<Long>

    fun observeBurnedCaloriesInTimeRange(startTime: Long, endTime: Long): Flow<Double>
}

internal class BurnedCaloriesDatasourceImpl @Inject constructor(
    private val burnedCaloriesDao: BurnedCaloriesDao
) : BurnedCaloriesDatasource {

    override suspend fun insertBurnedCalories(data: List<HealthBurnedCaloriesEntity>): List<Long> {
        return burnedCaloriesDao.insertBurnedCalories(data)
    }

    override fun observeBurnedCaloriesInTimeRange(startTime: Long, endTime: Long): Flow<Double> {
        return burnedCaloriesDao.observeBurnedCaloriesInTimeRange(startTime, endTime)
    }

}