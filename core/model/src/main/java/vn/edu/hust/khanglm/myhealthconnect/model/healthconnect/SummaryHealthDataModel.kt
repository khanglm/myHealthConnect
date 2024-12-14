package vn.edu.hust.khanglm.myhealthconnect.model.healthconnect

data class SummaryHealthDataModel(
    val totalSteps: Long,
    val totalBurnedCalories: Double,
    val avgHeartRate: Double,
    val distance: Double
)
