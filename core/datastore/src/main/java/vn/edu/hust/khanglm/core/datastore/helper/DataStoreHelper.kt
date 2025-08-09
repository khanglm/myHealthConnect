package vn.edu.hust.khanglm.core.datastore.helper

import kotlinx.coroutines.flow.Flow

internal interface DataStoreHelper {

    fun observeIntValue(key: String, defaultValue: Int): Flow<Int>
    fun observeStringValue(key: String, defaultValue: String): Flow<String>
    fun observeLongValue(key: String, defaultValue: Long): Flow<Long>
    fun observeDoubleValue(key: String, defaultValue: Double): Flow<Double>
    fun observeFloatValue(key: String, defaultValue: Float): Flow<Float>
    fun observeBooleanValue(key: String, defaultValue: Boolean): Flow<Boolean>

    suspend fun saveString(key: String, value: String): Boolean
    suspend fun saveInt(key: String, value: Int): Boolean
    suspend fun saveLong(key: String, value: Long): Boolean
    suspend fun saveDouble(key: String, value: Double): Boolean
    suspend fun saveFloat(key: String, value: Float): Boolean
    suspend fun saveBoolean(key: String, value: Boolean): Boolean

}