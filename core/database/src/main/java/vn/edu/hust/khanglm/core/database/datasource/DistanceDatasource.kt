package vn.edu.hust.khanglm.core.database.datasource

import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.dao.DistanceDao
import vn.edu.hust.khanglm.core.database.entities.DistanceEntity
import javax.inject.Inject

interface DistanceDatasource {
    suspend fun insertDistanceData(data: List<DistanceEntity>): List<Long>

    fun observeTotalDistanceInTimeRange(startTime: Long, endTime: Long): Flow<Double>
}

internal class DistanceDatasourceImpl @Inject constructor(
    private val distanceDao: DistanceDao
) : DistanceDatasource {
    override suspend fun insertDistanceData(data: List<DistanceEntity>): List<Long> {
        return distanceDao.insertDistanceData(data)
    }

    override fun observeTotalDistanceInTimeRange(startTime: Long, endTime: Long): Flow<Double> {
        return distanceDao.observeTotalDistanceToday(startTime, endTime)
    }
}