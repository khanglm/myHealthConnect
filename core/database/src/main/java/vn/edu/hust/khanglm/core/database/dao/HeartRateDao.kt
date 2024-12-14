package vn.edu.hust.khanglm.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.entities.HealthHeartRateEntity

@Dao
internal interface HeartRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeartRateData(heartRateData: List<HealthHeartRateEntity>): List<Long>

    @Query(
        "SELECT AVG(heart_rate) FROM heart_rate_info WHERE start_time >= :startTime AND end_time <= :endTime"
    )
    fun observeAverageHeartRateToday(startTime: Long, endTime: Long): Flow<Double>

}