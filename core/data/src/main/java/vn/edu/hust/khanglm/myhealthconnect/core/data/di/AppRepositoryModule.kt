package vn.edu.hust.khanglm.myhealthconnect.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.HealthDataTodayRepository
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.HealthDataTodayRepositoryImpl
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.SyncHealthDataRepository
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.SyncHealthDataRepositoryImpl
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.TimeSyncDataRepository
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.TimeSyncDataRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
internal interface AppRepositoryModule {

    @Binds
    fun bindSyncHealthDataRepository(impl: SyncHealthDataRepositoryImpl): SyncHealthDataRepository

    @Binds
    fun bindHealthDataTodayRepository(impl: HealthDataTodayRepositoryImpl): HealthDataTodayRepository

    @Binds
    fun bindTimeSyncDataRepository(impl: TimeSyncDataRepositoryImpl): TimeSyncDataRepository

}