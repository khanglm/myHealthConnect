package vn.edu.hust.khanglm.core.datastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.edu.hust.khanglm.core.datastore.AppDataStoreDataSource
import vn.edu.hust.khanglm.core.datastore.AppDataStoreDataSourceImpl
import vn.edu.hust.khanglm.core.datastore.helper.DataStoreHelper
import vn.edu.hust.khanglm.core.datastore.helper.DataStoreHelperImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    fun bindsDataStoreHelper(
        impl: DataStoreHelperImpl
    ): DataStoreHelper

    @Binds
    fun bindsDataStoreDataSource(
        impl: AppDataStoreDataSourceImpl
    ): AppDataStoreDataSource
}