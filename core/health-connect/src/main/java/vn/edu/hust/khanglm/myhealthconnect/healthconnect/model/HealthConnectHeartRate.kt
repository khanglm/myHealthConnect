package vn.edu.hust.khanglm.myhealthconnect.healthconnect.model

data class HealthConnectHeartRate(
    val id: String,
    val heartRate: Double,
    val startTime: Long,
    val endTime: Long
)
