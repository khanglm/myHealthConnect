package vn.edu.hust.khanglm.myhealthconnect.healthconnect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasourceImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface HealthConnectDatasourceModule {

    @Binds
    fun bindHealthConnectDatasource(impl: HealthConnectDatasourceImpl): HealthConnectDatasource

}