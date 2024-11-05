package vn.edu.hust.khanglm.myhealthconnect.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.edu.hust.khanglm.myhealthconnect.core.data.SyncHealthDataRepository
import vn.edu.hust.khanglm.myhealthconnect.core.data.SyncHealthDataRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
internal interface AppRepositoryModule {

    @Binds
    fun bindSyncHealthDataRepository(impl: SyncHealthDataRepositoryImpl): SyncHealthDataRepository

}