package vn.edu.hust.khanglm.features.home.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.TimeSyncDataRepository
import javax.inject.Inject

internal class GetFirstTimeSyncDataUseCase @Inject constructor(
    private val repository: TimeSyncDataRepository,
    @Dispatcher(MhcDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Long = withContext(ioDispatcher) {
        runCatching { repository.getFirstTimeSyncDataInMillis() }
            .fold(
                onSuccess = { it },
                onFailure = {
                    val (startTimeSync, _) = calculateTimeSyncData()
                    startTimeSync
                }
            )
    }

}