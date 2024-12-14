package vn.edu.hust.khanglm.features.home.model

import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.SummaryHealthDataModel

data class HomeUIModel(
    val healthSummaryData: SummaryHealthDataModel,
    val selectedTime: String
)
