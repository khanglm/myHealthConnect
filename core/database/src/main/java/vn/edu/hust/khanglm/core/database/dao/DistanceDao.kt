package vn.edu.hust.khanglm.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.entities.DistanceEntity

@Dao
interface DistanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistanceData(heartRateData: List<DistanceEntity>): List<Long>

    @Query(
        "SELECT SUM(distance) FROM table_distance WHERE start_time >= :startTime AND end_time <= :endTime"
    )
    fun observeTotalDistanceToday(startTime: Long, endTime: Long): Flow<Double>
}