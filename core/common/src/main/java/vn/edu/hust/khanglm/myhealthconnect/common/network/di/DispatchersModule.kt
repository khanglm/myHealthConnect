package vn.edu.hust.khanglm.myhealthconnect.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    @Dispatcher(MhcDispatcher.Default)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    @Dispatcher(MhcDispatcher.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}