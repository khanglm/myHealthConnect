package vn.edu.hust.khanglm.myhealthconnect.core.data.repository

import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasource
import javax.inject.Inject

interface TimeSyncDataRepository {
    suspend fun getFirstTimeSyncDataInMillis(): Long
}

internal class TimeSyncDataRepositoryImpl @Inject constructor(
    private val stepsCountDatasource: StepsCountDatasource
) : TimeSyncDataRepository {

    override suspend fun getFirstTimeSyncDataInMillis(): Long {
        return stepsCountDatasource.getFirstTimeSyncDataInMillis()
    }

}