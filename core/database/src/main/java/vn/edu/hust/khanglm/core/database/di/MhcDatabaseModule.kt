package vn.edu.hust.khanglm.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vn.edu.hust.khanglm.core.database.MhcDatabase
import vn.edu.hust.khanglm.core.database.dao.BurnedCaloriesDao
import vn.edu.hust.khanglm.core.database.dao.DistanceDao
import vn.edu.hust.khanglm.core.database.dao.HeartRateDao
import vn.edu.hust.khanglm.core.database.dao.StepsDao
import vn.edu.hust.khanglm.core.database.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MhcDatabaseModule {

    @Provides
    @Singleton
    internal fun provideMhcDatabase(
        @ApplicationContext context: Context
    ): MhcDatabase = Room.databaseBuilder(
        context = context,
        klass = MhcDatabase::class.java,
        name = MhcDatabase.DB_NAME
    ).build()

    @Provides
    internal fun provideUserDao(
        db: MhcDatabase
    ): UserDao = db.getUserDao()

    @Provides
    internal fun provideStepsCountDao(
        db: MhcDatabase
    ): StepsDao = db.getStepsDao()

    @Provides
    internal fun provideHeartRateDao(
        db: MhcDatabase
    ): HeartRateDao = db.getHeartRateDao()

    @Provides
    internal fun provideBurnedCaloriesDao(
        db: MhcDatabase
    ): BurnedCaloriesDao = db.getBurnedCaloriesDao()

    @Provides
    internal fun provideDistanceDao(
        db: MhcDatabase
    ): DistanceDao = db.getDistanceDao()

}