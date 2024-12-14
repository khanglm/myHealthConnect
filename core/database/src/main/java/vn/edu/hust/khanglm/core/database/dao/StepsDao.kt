package vn.edu.hust.khanglm.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.entities.HealthStepsDataEntity

@Dao
internal interface StepsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStepsData(data: List<HealthStepsDataEntity>): List<Long>

    @Query(
        "SELECT SUM(steps_info.steps) FROM steps_info WHERE steps_info.end_time <= :endTime AND steps_info.start_time >= :startTime"
    )
    fun observeTotalStepInTimeRange(startTime: Long, endTime: Long): Flow<Long>

    @Query(
        "SELECT MIN(start_time) FROM steps_info WHERE start_time != 0"
    )
    suspend fun getFirstTimeSyncDataInMillis(): Long
}