package vn.edu.hust.khanglm.core.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasource
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasourceImpl
import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasource
import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasourceImpl
import vn.edu.hust.khanglm.core.database.datasource.UserDatasource
import vn.edu.hust.khanglm.core.database.datasource.UserDatasourceImpl

@InstallIn(SingletonComponent::class)
@Module
internal interface DatasourceModule {

    @Binds
    fun bindUserDatasource(
        userDatasourceImpl: UserDatasourceImpl
    ): UserDatasource

    @Binds
    fun bindStepsDatasource(
        stepsCountDatasource: StepsCountDatasourceImpl
    ): StepsCountDatasource

    @Binds
    fun bindHeartRateDatasource(
        heartRateDatasource: HeartRateDatasourceImpl
    ): HeartRateDatasource

}