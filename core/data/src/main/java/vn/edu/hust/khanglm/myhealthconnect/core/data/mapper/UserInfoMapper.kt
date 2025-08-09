package vn.edu.hust.khanglm.myhealthconnect.core.data.mapper

import vn.edu.hust.khanglm.core.database.entities.UserEntity
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo.UserInfo

fun UserEntity.toModel(): UserInfo {
    return UserInfo(
        id = this.id,
        userName = this.userName,
        dob = this.dob,
        goalSteps = this.goalSteps,
        gender = this.gender
    )
}

fun UserInfo.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        userName = this.userName,
        dob = this.dob,
        goalSteps = this.goalSteps,
        gender = this.gender
    )
}