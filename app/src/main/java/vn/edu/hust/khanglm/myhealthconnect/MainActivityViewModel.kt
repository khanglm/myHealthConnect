package vn.edu.hust.khanglm.myhealthconnect

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.SyncHealthDataRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val syncHealthDataRepository: SyncHealthDataRepository
) : ViewModel() {

    fun startSyncStepsData() {
        syncHealthDataRepository.startWorkerSyncStepData()
    }

    fun startSyncHeartRateData() {
        syncHealthDataRepository.startWorkerSyncHearRateData()
    }

    fun startSyncBurnedCaloriesData() {
        syncHealthDataRepository.startWorkerSyncBurnedCaloriesData()
    }

    fun startSyncDistanceData() {
        syncHealthDataRepository.startWorkerSyncDistanceData()
    }
}