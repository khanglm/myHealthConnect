package vn.edu.hust.khanglm.myhealthconnect.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val mhcDispatcher: MhcDispatcher)

enum class MhcDispatcher {
    Default,
    IO
}