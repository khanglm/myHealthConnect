package vn.edu.hust.khanglm.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vn.edu.hust.khanglm.features.home.domain.GetFirstTimeSyncDataUseCase
import vn.edu.hust.khanglm.features.home.domain.ObserveHealthDataUseCase
import vn.edu.hust.khanglm.features.home.model.HomeUIModel
import vn.edu.hust.khanglm.myhealthconnect.common.calculateTimeSyncData
import vn.edu.hust.khanglm.myhealthconnect.common.convertToDDMMMYYYYFormat
import vn.edu.hust.khanglm.myhealthconnect.common.getRangeTimeInDay
import vn.edu.hust.khanglm.myhealthconnect.common.result.Result
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    observeHealthDataUseCase: ObserveHealthDataUseCase,
    getFirstTimeSyncDataUseCase: GetFirstTimeSyncDataUseCase
) : ViewModel() {

    private val _selectedTime: MutableStateFlow<Long> = MutableStateFlow(Instant.now().toEpochMilli())
    val selectedTime = _selectedTime.asStateFlow()

    var firstTimeSyncData = calculateTimeSyncData().first
        private set

    init {
        viewModelScope.launch {
            firstTimeSyncData = getFirstTimeSyncDataUseCase()
        }
    }

    val uiState: StateFlow<HomeUIState> = _selectedTime.flatMapLatest {
        val (startTime, endTime) = it.getRangeTimeInDay()
        observeHealthDataUseCase(startTime, endTime)
    }
        .mapLatest { result ->
            when (result) {
                is Result.Success -> {
                    HomeUIState.Success(
                        data = HomeUIModel(
                            healthSummaryData = result.data,
                            selectedTime = _selectedTime.value.convertToDDMMMYYYYFormat()
                        )
                    )
                }
                is Result.Error -> {
                    HomeUIState.Error(result.throwable)
                }
                Result.Loading -> {
                    HomeUIState.Loading
                }
            }
        }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HomeUIState.Loading
        )

    fun setNewTimeSelected(newTimeInMillis: Long) {
        _selectedTime.tryEmit(newTimeInMillis)
    }

}

sealed interface HomeUIState {

    data class Success(val data: HomeUIModel) : HomeUIState

    data class Error(val throwable: Throwable?) : HomeUIState

    data object Loading : HomeUIState

}