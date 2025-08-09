package vn.edu.hust.khanglm.myhealthconnect.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.edu.hust.khanglm.core.database.datasource.UserDatasource
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toModel
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo.UserInfo
import javax.inject.Inject

interface UserInfoRepository {

    fun observeUserInfo(): Flow<UserInfo?>

    suspend fun updateUserInfo(userInfo: UserInfo): Long

}

internal class UserInfoRepositoryImpl @Inject constructor(
    private val userDatasource: UserDatasource
) : UserInfoRepository {
    override fun observeUserInfo(): Flow<UserInfo?> {
        return userDatasource.observeUserInfo().map {
            it?.toModel()
        }
    }

    override suspend fun updateUserInfo(userInfo: UserInfo): Long {
        return userDatasource.insertUserInfo(userInfo.toEntity())
    }

}