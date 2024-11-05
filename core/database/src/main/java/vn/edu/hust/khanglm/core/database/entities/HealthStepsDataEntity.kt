package vn.edu.hust.khanglm.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps_info")
data class HealthStepsDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "user_id", index = true)
    val userId: String,
    @ColumnInfo(name = "start_time", index = true)
    val startTime: Long,
    @ColumnInfo(name = "end_time", index = true)
    val endTime: Long,
    @ColumnInfo(name = "steps")
    val steps: Long
)
