package vn.edu.hust.khanglm.features.home

import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vn.edu.hust.khanglm.core.ui.datepicker.DatePickerModal
import vn.edu.hust.khanglm.features.home.ui.HealthDataTrackingCard
import vn.edu.hust.khanglm.features.home.ui.StepsTrackingCard
import vn.edu.hust.khanglm.features.home.ui.UserInfoSummary
import vn.edu.hust.khanglm.core.ui.R as coreUI

@Composable
internal fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentTimeFilter by viewModel.selectedTime.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        selectedTime = currentTimeFilter,
        firstTimeSyncData = viewModel.firstTimeSyncData,
        onChangeTimeFilter = {
            viewModel.setNewTimeSelected(it)
        }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
    selectedTime: Long,
    firstTimeSyncData: Long,
    onChangeTimeFilter: (Long) -> Unit
) {
    var shouldShowDateTimePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedTime,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val currentTime = Calendar.getInstance(TimeZone.getDefault()).timeInMillis
                return utcTimeMillis in firstTimeSyncData..currentTime
            }
        }
    )
    when (uiState) {
        HomeUIState.Loading -> {
            //TODO show loading
        }
        is HomeUIState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                UserInfoSummary(
                    userName = "Le Minh Khang",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    modifier = Modifier
                        .background(Color(0x1A2F80ED), RoundedCornerShape(20.dp))
                        .clickable {
                            shouldShowDateTimePicker = true
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = uiState.data.selectedTime,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = stringResource(R.string.features_home_your_activity),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalStaggeredGrid (
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    verticalItemSpacing = 12.dp,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item(key = "step") {
                        StepsTrackingCard(
                            totalSteps = uiState.data.healthSummaryData.totalSteps,
                            target = 10000L
                        )
                    }
                    item(key = "burned_calories") {
                        HealthDataTrackingCard(
                            value = uiState.data.healthSummaryData.totalBurnedCalories,
                            title = stringResource(R.string.features_home_burned_calories),
                            unit = stringResource(R.string.features_home_burned_calories_unit),
                            iconResId = R.drawable.features_home_icon_burned_calories,
                            imageGraph = R.drawable.features_home_burned_calories_graph
                        )
                    }
                    item(key = "heart_rate") {
                        HealthDataTrackingCard(
                            value = uiState.data.healthSummaryData.avgHeartRate,
                            title = "Heart Rate",
                            unit = "bpm",
                            iconResId = R.drawable.features_home_icon_heart_rate,
                            imageGraph = R.drawable.features_home_heart_rate_graph
                        )
                    }
                    item(key = "distance") {
                        HealthDataTrackingCard(
                            value = uiState.data.healthSummaryData.distance,
                            title = "Distance",
                            unit = "km",
                            iconResId = coreUI.drawable.core_ui_icon_walker,
                            imageGraph = R.drawable.features_home_distance_graph
                        )
                    }
                }
            }

        }
        is HomeUIState.Error -> {
            //TODO show error
        }
    }
    if (shouldShowDateTimePicker) {
        DatePickerModal(
            datePickerState = datePickerState,
            onConfirmClicked = {
                onChangeTimeFilter(it)
                shouldShowDateTimePicker = false
            },
            onDismissRequest = {
                shouldShowDateTimePicker = false
            }
        )
    }
}