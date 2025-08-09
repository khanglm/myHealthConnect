package vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo

data class UserInfo(
    val id: String,
    val userName: String,
    val dob: String,
    val gender: Int,
    val goalSteps: Long
)
