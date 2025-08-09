package vn.edu.hust.khanglm.myhealthconnect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vn.edu.hust.khanglm.myhealthconnect.core.data.repository.SyncHealthDataRepository
import vn.edu.hust.khanglm.myhealthconnect.domain.CheckInputUserInfoUseCase
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor(
    private val syncHealthDataRepository: SyncHealthDataRepository,
    checkInputUserInfoUseCase: CheckInputUserInfoUseCase
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

    val isUserInputInfo = checkInputUserInfoUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )
}