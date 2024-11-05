package vn.edu.hust.khanglm.myhealthconnect.core.data.mapper

import vn.edu.hust.khanglm.core.database.entities.HealthBurnedCaloriesEntity
import vn.edu.hust.khanglm.core.database.entities.HealthHeartRateEntity
import vn.edu.hust.khanglm.core.database.entities.HealthStepsDataEntity
import vn.edu.hust.khanglm.myhealthconnect.common.convertToTimeMillis
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.BurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.HeartRate
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.StepsCount

fun StepsCount.toEntity(): HealthStepsDataEntity {
    return HealthStepsDataEntity(
        id = this.id,
        startTime = this.startTime.convertToTimeMillis(),
        endTime = this.endTime.convertToTimeMillis(),
        userId = "",
        steps = this.stepsCount
    )
}

fun HeartRate.toEntity(): HealthHeartRateEntity {
    return HealthHeartRateEntity(
        id = this.id,
        startTime = this.startTime.convertToTimeMillis(),
        endTime = this.endTime.convertToTimeMillis(),
        userId = "",
        heartRate = this.heartRate
    )
}

fun BurnedCalories.toEntity(): HealthBurnedCaloriesEntity {
    return HealthBurnedCaloriesEntity(
        id = this.id,
        startTime = this.startTime.convertToTimeMillis(),
        endTime = this.endTime.convertToTimeMillis(),
        userId = "",
        burnedCalories = this.burnedCalories
    )
}