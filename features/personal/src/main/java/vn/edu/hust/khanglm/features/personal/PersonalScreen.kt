package vn.edu.hust.khanglm.features.personal

import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import vn.edu.hust.khanglm.core.ui.datepicker.DatePickerModal
import vn.edu.hust.khanglm.features.personal.ui.Avatar
import vn.edu.hust.khanglm.features.personal.ui.InfoSection
import vn.edu.hust.khanglm.myhealthconnect.common.convertToBirthdayFormat
import vn.edu.hust.khanglm.myhealthconnect.common.convertToTimeMillis
import vn.edu.hust.khanglm.myhealthconnect.model.healthconnect.userinfo.UserInfo
import java.util.Date

const val MIN_AGE: Long = 567648000000

@Composable
fun PersonalRoute(
    modifier: Modifier = Modifier,
    viewModel: PersonalViewModel = hiltViewModel(),
    canGoBack: Boolean = true,
    goBack: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var shouldShowError by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        val observeShowErrorJob = coroutineScope.launch {
            viewModel.showErrorEvent.collect {
                shouldShowError = true
            }
        }
        val observeUpdateInfoJob = coroutineScope.launch {
            viewModel.updateUserInfoSuccess.collect {
                goBack()
            }
        }
        onDispose {
            observeShowErrorJob.cancel()
            observeUpdateInfoJob.cancel()
        }
    }

    PersonalScreen(
        uiState = uiState,
        modifier = modifier,
        onEditInfoClicked = viewModel::updateUserInfo,
        canGoBack = canGoBack,
        shouldShowError = shouldShowError,
        goBack = goBack,
        resetShowError = {
            shouldShowError = false
        }
    )
}

@Composable
private fun PersonalScreen(
    uiState: PersonalScreenState,
    modifier: Modifier = Modifier,
    canGoBack: Boolean = true,
    shouldShowError: Boolean = false,
    onEditInfoClicked: (UserInfo) -> Unit = {},
    goBack: () -> Unit = {},
    resetShowError: () -> Unit = {}
) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .imePadding()
    ) {
        when (uiState) {
            PersonalScreenState.Loading -> {
                Unit
            }

            is PersonalScreenState.Success -> {
                UserInfoUI(
                    modifier = Modifier,
                    userInfo = uiState.userInfo,
                    onEditInfoClicked = onEditInfoClicked,
                    resetShowError = resetShowError,
                    shouldShowError = shouldShowError
                )
            }

            is PersonalScreenState.Error -> {
                Unit
            }
        }

        if (canGoBack) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        goBack()
                    },
                painter = painterResource(R.drawable.features_personal_icon_back),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserInfoUI(
    userInfo: UserInfo,
    modifier: Modifier = Modifier,
    shouldShowError: Boolean = false,
    onEditInfoClicked: (UserInfo) -> Unit = {},
    resetShowError: () -> Unit = {}
) {
    var userName by remember { mutableStateOf(userInfo.userName) }
    var targetSteps by remember { mutableStateOf(userInfo.goalSteps.toString()) }
    var dob by remember { mutableStateOf(userInfo.dob) }
    var shouldShowDateTimePicker by remember { mutableStateOf(false) }
    var shouldShowErrorDob by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = dob.convertToTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val currentTime = Calendar.getInstance(TimeZone.getDefault()).timeInMillis
                return utcTimeMillis <= currentTime
            }
        }
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(key = "avatar") {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Avatar(
                        modifier = Modifier,
                        userName = userInfo.userName,
                    )
                    Spacer(Modifier.height(24.dp))
                }
            }

            item(key = "user_name") {
                InfoSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = "User name",
                    value = userName,
                    isRequired = true,
                    onTextChange = {
                        userName = it
                    },
                    isError = shouldShowError && userName.isEmpty()
                )
            }

            item(key = "dob") {
                InfoSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            shouldShowDateTimePicker = true
                        },
                    title = "Date of Birth",
                    value = dob,
                    isRequired = true,
                    isEnable = false,
                    readOnly = true,
                    isError = shouldShowError && dob.isEmpty()
                )
            }

            item(key = "goal_steps") {
                InfoSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Target Step each day",
                    value = targetSteps,
                    isRequired = true,
                    isEnable = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onTextChange = {
                        targetSteps = it
                    },
                    isError = shouldShowError && targetSteps.isEmpty()
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                resetShowError()
                onEditInfoClicked(
                    userInfo.copy(
                        userName = userName,
                        dob = dob,
                        gender = 1,
                        goalSteps = targetSteps.toLongOrNull() ?: -1
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F80ED)
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Save"
            )
        }
    }

    if (shouldShowDateTimePicker) {
        DatePickerModal(
            datePickerState = datePickerState,
            onConfirmClicked = {
                val currentTimeMillis = Date().time
                Log.d("KhangLM", "${currentTimeMillis - it}")
                if (currentTimeMillis - it >= MIN_AGE) {
                    dob = it.convertToBirthdayFormat()
                    shouldShowDateTimePicker = false
                } else {
                    shouldShowErrorDob = true
                }

            },
            onDismissRequest = {
                shouldShowDateTimePicker = false
            }
        )
    }

    if (shouldShowErrorDob) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = {
                shouldShowErrorDob = false
            },
            confirmButton = {
                Text(
                    modifier = Modifier.clickable {
                        shouldShowErrorDob = false
                    },
                    text = "OK"
                )
            },
            dismissButton = {
                Text(
                    modifier = Modifier.clickable {
                        shouldShowErrorDob = false
                    },
                    text = "Cancel"
                )
            },
            title = {
                Text(
                    text = "Error"
                )
            },
            text = {
                Text(
                    text = "You must be greater than 18 years old to use this Application"
                )
            }
        )
    }
}