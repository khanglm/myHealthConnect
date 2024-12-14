package vn.edu.hust.khanglm.features.home.domain

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.result.Result
import vn.edu.hust.khanglm.myhealthconnect.common.result.asResult
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.HealthDataTodayRepository
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.SummaryHealthDataModel
import javax.inject.Inject

internal class ObserveHealthDataUseCase @Inject constructor(
    private val healthDataTodayRepository: HealthDataTodayRepository,
    @Dispatcher(MhcDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(startTime: Long, endTime: Long): Flow<Result<SummaryHealthDataModel>> {
        Log.d("KhangLm2", "ObserveHealthDataUseCase - invoke - endTime = $endTime, startTime = $startTime")
        return combine(
            healthDataTodayRepository.observeTotalSteps(startTime, endTime),
            healthDataTodayRepository.observeTotalBurnedCalories(startTime, endTime),
            healthDataTodayRepository.observeAvgHeartRate(startTime, endTime),
            healthDataTodayRepository.observeDistance(startTime, endTime)
        ) { steps, burnedCalories, heartRate, distance ->
            SummaryHealthDataModel(
                totalSteps = steps,
                totalBurnedCalories = burnedCalories,
                avgHeartRate = heartRate,
                distance = distance
            )
        }
            .asResult()
            .flowOn(ioDispatcher)
            .distinctUntilChanged()
    }
}