package vn.edu.hust.khanglm.features.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vn.edu.hust.khanglm.features.personal.domain.ObserveUserInfoUseCase
import vn.edu.hust.khanglm.features.personal.domain.UpdateUserInfoUseCase
import vn.edu.hust.khanglm.myhealthconnect.common.result.Result
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo.UserInfo
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    observeUserInfoUseCase: ObserveUserInfoUseCase,
    private val updateUserInfoUseCase: UpdateUserInfoUseCase
) : ViewModel() {

    private val _showErrorEvent: MutableSharedFlow<Unit> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val _updateUserInfoSuccess: MutableSharedFlow<Unit> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val showErrorEvent = _showErrorEvent.asSharedFlow()
    val updateUserInfoSuccess = _updateUserInfoSuccess.asSharedFlow()

    val uiState = observeUserInfoUseCase()
        .map {
            when (it) {
                Result.Loading -> PersonalScreenState.Loading
                is Result.Success -> PersonalScreenState.Success(it.data)
                is Result.Error -> PersonalScreenState.Error(it.throwable)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = PersonalScreenState.Loading
        )

    fun updateUserInfo(userInfo: UserInfo) {
        viewModelScope.launch {
            if (userInfo.userName.isEmpty() || userInfo.dob.isEmpty() || userInfo.goalSteps <= 0) {
                _showErrorEvent.tryEmit(Unit)
                return@launch
            }
            updateUserInfoUseCase(
                userInfo
            )
            _updateUserInfoSuccess.tryEmit(Unit)
        }
    }
}

sealed interface PersonalScreenState {

    data object Loading : PersonalScreenState

    data class Success(val userInfo: UserInfo) : PersonalScreenState

    data class Error(val throwable: Throwable?) : PersonalScreenState

}
