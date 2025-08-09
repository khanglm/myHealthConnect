package vn.edu.hust.khanglm.myhealthconnect.core.data.mapper

import vn.edu.hust.khanglm.core.database.entities.DistanceEntity
import vn.edu.hust.khanglm.core.database.entities.HealthBurnedCaloriesEntity
import vn.edu.hust.khanglm.core.database.entities.HealthHeartRateEntity
import vn.edu.hust.khanglm.core.database.entities.HealthStepsDataEntity
import vn.edu.hust.khanglm.myhealthconnect.common.convertToDDMMMYYYYFormat
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectBurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectDistance
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectHeartRate
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.model.HealthConnectStepsCount
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.healthdata.BurnedCalories
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.healthdata.Distance
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.healthdata.HeartRate
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.healthdata.StepsCount

fun HealthConnectStepsCount.toEntity(): HealthStepsDataEntity {
    return HealthStepsDataEntity(
        id = this.id,
        startTime = this.startTime,
        endTime = this.endTime,
        userId = "",
        steps = this.stepsCount
    )
}

fun HealthConnectHeartRate.toEntity(): HealthHeartRateEntity {
    return HealthHeartRateEntity(
        id = this.id,
        startTime = this.startTime,
        endTime = this.endTime,
        userId = "",
        heartRate = this.heartRate
    )
}

fun HealthConnectBurnedCalories.toEntity(): HealthBurnedCaloriesEntity {
    return HealthBurnedCaloriesEntity(
        id = this.id,
        startTime = this.startTime,
        endTime = this.endTime,
        userId = "",
        burnedCalories = this.burnedCalories
    )
}

fun HealthConnectDistance.toEntity(): DistanceEntity {
    return DistanceEntity(
        id = this.id,
        startTime = this.startTime,
        endTime = this.endTime,
        userId = "",
        distance = this.distance
    )
}

fun HealthStepsDataEntity.toModel(): StepsCount {
    return StepsCount(
        id = this.id,
        startTime = this.startTime.convertToDDMMMYYYYFormat(),
        endTime = this.endTime.convertToDDMMMYYYYFormat(),
        stepsCount = this.steps
    )
}

fun HealthHeartRateEntity.toModel(): HeartRate {
    return HeartRate(
        id = this.id,
        startTime = this.startTime.convertToDDMMMYYYYFormat(),
        endTime = this.endTime.convertToDDMMMYYYYFormat(),
        heartRate = this.heartRate
    )
}

fun HealthBurnedCaloriesEntity.toModel(): BurnedCalories {
    return BurnedCalories(
        id = this.id,
        startTime = this.startTime.convertToDDMMMYYYYFormat(),
        endTime = this.endTime.convertToDDMMMYYYYFormat(),
        burnedCalories = this.burnedCalories
    )
}

fun DistanceEntity.toModel(): Distance {
    return Distance(
        id = this.id,
        startTime = this.startTime.convertToDDMMMYYYYFormat(),
        endTime = this.endTime.convertToDDMMMYYYYFormat(),
        distance = this.distance
    )
}

