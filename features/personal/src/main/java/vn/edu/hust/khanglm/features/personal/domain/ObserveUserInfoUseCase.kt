package vn.edu.hust.khanglm.features.personal.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.result.Result
import vn.edu.hust.khanglm.myhealthconnect.common.result.asResult
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.UserInfoRepository
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo.UserInfo
import java.util.UUID
import javax.inject.Inject

class ObserveUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    @Dispatcher(MhcDispatcher.IO) private val  ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Result<UserInfo>> {
        return userInfoRepository.observeUserInfo()
            .map {
                it ?: UserInfo(
                    id = UUID.randomUUID().toString(),
                    userName = "",
                    gender = 1,
                    dob = "",
                    goalSteps = 6000L
                )
            }
            .asResult()
            .distinctUntilChanged()
            .flowOn(ioDispatcher)
    }

}