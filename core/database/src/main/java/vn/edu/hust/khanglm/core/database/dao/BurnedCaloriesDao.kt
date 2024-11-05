package vn.edu.hust.khanglm.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.entities.HealthBurnedCaloriesEntity

@Dao
internal interface BurnedCaloriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBurnedCalories(data: List<HealthBurnedCaloriesEntity>): List<Long>

    @Query(
        "SELECT SUM(table_burned_calories.burned_calories) FROM table_burned_calories WHERE table_burned_calories.start_time <= :endTime AND table_burned_calories.end_time >= :startTime"
    )
    fun observeBurnedCaloriesInTimeRange(startTime: Long, endTime: Long): Flow<Double>
}