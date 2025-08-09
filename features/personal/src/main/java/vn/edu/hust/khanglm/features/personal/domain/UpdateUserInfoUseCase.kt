package vn.edu.hust.khanglm.features.personal.domain

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.UserInfoRepository
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo.UserInfo
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    @Dispatcher(MhcDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userInfo: UserInfo): Long {
        return try {
            withContext(ioDispatcher) {
                userInfoRepository.updateUserInfo(userInfo)
            }
        } catch (e: Exception) {
            Log.e("UpdateUserInfoUseCase", "Update User Info exception ${e.message}", e)
            -1L
        }
    }

}