package vn.edu.hust.khanglm.myhealthconnect.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import vn.edu.hust.khanglm.myhealthconnect.common.network.Dispatcher
import vn.edu.hust.khanglm.myhealthconnect.common.network.MhcDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(
        @Dispatcher(MhcDispatcher.Default) defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

}