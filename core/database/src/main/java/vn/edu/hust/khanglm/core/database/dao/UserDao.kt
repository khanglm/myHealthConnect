package vn.edu.hust.khanglm.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.edu.hust.khanglm.core.database.entities.UserEntity

@Dao
internal interface UserDao {

    @Query("SELECT * FROM users")
    fun getUserInfo(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userEntity: UserEntity): Long

}