package vn.edu.hust.khanglm.core.datastore.helper

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DataStoreHelperImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreHelper {

    override fun observeIntValue(key: String, defaultValue: Int): Flow<Int> {
        return dataStore.data.map { it[intPreferencesKey(key)] ?: defaultValue }
    }

    override fun observeStringValue(key: String, defaultValue: String): Flow<String> {
        return dataStore.data.map { it[stringPreferencesKey(key)] ?: defaultValue }
    }

    override fun observeLongValue(key: String, defaultValue: Long): Flow<Long> {
        return dataStore.data.map { it[longPreferencesKey(key)] ?: defaultValue }
    }

    override fun observeDoubleValue(key: String, defaultValue: Double): Flow<Double> {
        return dataStore.data.map { it[doublePreferencesKey(key)] ?: defaultValue }
    }

    override fun observeFloatValue(key: String, defaultValue: Float): Flow<Float> {
        return dataStore.data.map { it[floatPreferencesKey(key)] ?: defaultValue }
    }

    override fun observeBooleanValue(key: String, defaultValue: Boolean): Flow<Boolean> {
        return dataStore.data.map { it[booleanPreferencesKey(key)] ?: defaultValue }
    }


    override suspend fun saveString(key: String, value: String): Boolean {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
        return true
    }

    override suspend fun saveInt(key: String, value: Int): Boolean {
        dataStore.edit {
            it[intPreferencesKey(key)] = value
        }
        return true
    }

    override suspend fun saveLong(key: String, value: Long): Boolean {
        dataStore.edit {
            it[longPreferencesKey(key)] = value
        }
        return true
    }

    override suspend fun saveDouble(key: String, value: Double): Boolean {
        dataStore.edit {
            it[doublePreferencesKey(key)] = value
        }
        return true
    }

    override suspend fun saveFloat(key: String, value: Float): Boolean {
        dataStore.edit {
            it[floatPreferencesKey(key)] = value
        }
        return true
    }

    override suspend fun saveBoolean(key: String, value: Boolean): Boolean {
        dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
        return true
    }
}