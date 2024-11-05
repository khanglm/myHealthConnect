package vn.edu.hust.khanglm.core.database.datasource

import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.dao.StepsDao
import vn.edu.hust.khanglm.core.database.entities.HealthStepsDataEntity
import javax.inject.Inject

interface StepsCountDatasource {
    suspend fun insertStepsData(data: List<HealthStepsDataEntity>): List<Long>

    fun observeTotalStepInTimeRange(startTime: Long, endTime: Long): Flow<Long>
}

internal class StepsCountDatasourceImpl @Inject constructor(
    private val stepsDao: StepsDao
) : StepsCountDatasource {

    override suspend fun insertStepsData(data: List<HealthStepsDataEntity>): List<Long> {
        return stepsDao.insertStepsData(data)
    }

    override fun observeTotalStepInTimeRange(startTime: Long, endTime: Long): Flow<Long> {
        return observeTotalStepInTimeRange(startTime, endTime)
    }

}