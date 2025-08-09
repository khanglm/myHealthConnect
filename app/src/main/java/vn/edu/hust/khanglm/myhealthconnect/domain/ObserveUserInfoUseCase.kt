package vn.edu.hust.khanglm.myhealthconnect.domain

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.UserInfoRepository
import javax.inject.Inject

internal class CheckInputUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    @Dispatcher(MhcDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Boolean> = userInfoRepository.observeUserInfo()
        .map { it != null }
        .catch {
            Log.e("CheckInputUserInfoUseCase", it.message.orEmpty(), it)
        }
        .flowOn(ioDispatcher)

}