package vn.edu.hust.khanglm.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.edu.hust.khanglm.core.database.dao.BurnedCaloriesDao
import vn.edu.hust.khanglm.core.database.dao.DistanceDao
import vn.edu.hust.khanglm.core.database.dao.HeartRateDao
import vn.edu.hust.khanglm.core.database.dao.StepsDao
import vn.edu.hust.khanglm.core.database.dao.UserDao
import vn.edu.hust.khanglm.core.database.entities.DistanceEntity
import vn.edu.hust.khanglm.core.database.entities.HealthBurnedCaloriesEntity
import vn.edu.hust.khanglm.core.database.entities.HealthHeartRateEntity
import vn.edu.hust.khanglm.core.database.entities.HealthStepsDataEntity
import vn.edu.hust.khanglm.core.database.entities.UserEntity

@Database(
    entities = [
        HealthHeartRateEntity::class,
        HealthStepsDataEntity::class,
        UserEntity::class,
        HealthBurnedCaloriesEntity::class,
        DistanceEntity::class
    ],
    version = 1
)
internal abstract class MhcDatabase : RoomDatabase() {

    internal abstract fun getUserDao(): UserDao
    internal abstract fun getStepsDao(): StepsDao
    internal abstract fun getHeartRateDao(): HeartRateDao
    internal abstract fun getBurnedCaloriesDao(): BurnedCaloriesDao
    internal abstract fun getDistanceDao(): DistanceDao

    companion object {
        const val DB_NAME = "mhc.db"
    }
}