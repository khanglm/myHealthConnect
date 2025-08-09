package vn.edu.hust.khanglm.core.database.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.edu.hust.khanglm.core.database.dao.UserDao
import vn.edu.hust.khanglm.core.database.entities.UserEntity
import javax.inject.Inject

interface UserDatasource {

    fun observeUserInfo(): Flow<UserEntity?>

    suspend fun insertUserInfo(userInfo: UserEntity): Long

}

internal class UserDatasourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserDatasource {

    override fun observeUserInfo(): Flow<UserEntity?> {
        return userDao.getUserInfo().map { it.firstOrNull() }
    }

    override suspend fun insertUserInfo(userInfo: UserEntity): Long {
        return userDao.insertUserInfo(userEntity = userInfo)
    }

}