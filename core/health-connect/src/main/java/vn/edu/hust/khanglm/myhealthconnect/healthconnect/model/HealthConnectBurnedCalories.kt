package vn.edu.hust.khanglm.myhealthconnect.healthconnect.model

data class HealthConnectBurnedCalories(
    val id: String,
    val burnedCalories: Double,
    val startTime: Long,
    val endTime: Long
)
