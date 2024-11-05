package vn.edu.hust.khanglm.core.database.datasource

import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.dao.HeartRateDao
import vn.edu.hust.khanglm.core.database.entities.HealthHeartRateEntity
import javax.inject.Inject

interface HeartRateDatasource {

    fun observeAverageHeartRate(startTime: Long, endTime: Long): Flow<Double>

    suspend fun insertHeartRateRecord(records: List<HealthHeartRateEntity>): List<Long>

}

internal class HeartRateDatasourceImpl @Inject constructor(
    private val heartRateDao: HeartRateDao
) : HeartRateDatasource {
    override fun observeAverageHeartRate(startTime: Long, endTime: Long): Flow<Double> {
        return heartRateDao.observeAverageHeartRateToday(startTime, endTime)
    }

    override suspend fun insertHeartRateRecord(records: List<HealthHeartRateEntity>): List<Long> {
        return heartRateDao.insertHeartRateData(records)
    }

}