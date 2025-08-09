package vn.edu.hust.khanglm.features.home.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.result.Result
import vn.edu.hust.khanglm.myhealthconnect.common.result.asResult
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.HealthDataTodayRepository
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.UserInfoRepository
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.healthdata.SummaryHealthDataModel
import javax.inject.Inject

internal class ObserveHealthDataUseCase @Inject constructor(
    private val healthDataTodayRepository: HealthDataTodayRepository,
    private val userInfoRepository: UserInfoRepository,
    @Dispatcher(MhcDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(startTime: Long, endTime: Long): Flow<Result<Pair<String, SummaryHealthDataModel>>> {
        return combine(
            healthDataTodayRepository.observeTotalSteps(startTime, endTime),
            healthDataTodayRepository.observeTotalBurnedCalories(startTime, endTime),
            healthDataTodayRepository.observeAvgHeartRate(startTime, endTime),
            healthDataTodayRepository.observeDistance(startTime, endTime),
            userInfoRepository.observeUserInfo().filterNotNull()
        ) { steps, burnedCalories, heartRate, distance, userInfo ->
            Pair(
                userInfo.userName,
                SummaryHealthDataModel(
                    totalSteps = steps,
                    totalBurnedCalories = burnedCalories,
                    avgHeartRate = heartRate,
                    distance = distance,
                    targetSteps = userInfo.goalSteps
                )
            )

        }
            .asResult()
            .flowOn(ioDispatcher)
            .distinctUntilChanged()
    }
}