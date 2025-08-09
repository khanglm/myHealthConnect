package vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.healthdata

data class SummaryHealthDataModel(
    val totalSteps: Long,
    val totalBurnedCalories: Double,
    val avgHeartRate: Double,
    val distance: Double,
    val targetSteps: Long
)
