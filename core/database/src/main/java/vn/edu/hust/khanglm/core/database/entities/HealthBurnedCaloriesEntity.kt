package vn.edu.hust.khanglm.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_burned_calories")
data class HealthBurnedCaloriesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id", index = true)
    val id: String,
    @ColumnInfo("user_id")
    val userId: String,
    @ColumnInfo(name = "start_time", index = true)
    val startTime: Long,
    @ColumnInfo(name = "end_time", index = true)
    val endTime: Long,
    @ColumnInfo("burned_calories")
    val burnedCalories: Double
)
