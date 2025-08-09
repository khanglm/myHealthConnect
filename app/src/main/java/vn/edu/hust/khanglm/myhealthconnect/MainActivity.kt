package vn.edu.hust.khanglm.myhealthconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import vn.edu.hust.khanglm.features.personal.PersonalRoute
import vn.edu.hust.khanglm.myhealthconnect.ui.MhcApp
import vn.edu.hust.khanglm.myhealthconnect.ui.rememberMhcAppState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val _viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            false
        }
        enableEdgeToEdge()
        setContent {
            val isUserInputInfo by _viewModel.isUserInputInfo.collectAsStateWithLifecycle()
            if (isUserInputInfo) {
                val appState = rememberMhcAppState()
                RequestHealthConnectPermission()
                MhcApp(
                    appState = appState
                )
            } else {
                PersonalRoute(
                    modifier = Modifier.navigationBarsPadding(),
                    canGoBack = false
                )
            }
        }
    }

    @Composable
    private fun RequestHealthConnectPermission() {
        val permission = PermissionController.createRequestPermissionResultContract()
        val permissionLauncher = rememberLauncherForActivityResult(permission) { permissionResult ->
            permissionResult.forEach { permission ->
                when (permission) {
                    HealthPermission.getReadPermission(StepsRecord::class) -> {
                        _viewModel.startSyncStepsData()

                    }

                    HealthPermission.getReadPermission(HeartRateRecord::class) -> {
                        _viewModel.startSyncHeartRateData()

                    }

                    HealthPermission.getReadPermission(TotalCaloriesBurnedRecord::class) -> {
                        _viewModel.startSyncBurnedCaloriesData()
                    }

                    HealthPermission.getReadPermission(DistanceRecord::class) -> {
                        _viewModel.startSyncDistanceData()
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            permissionLauncher.launch(
                setOf(
                    HealthPermission.getReadPermission(StepsRecord::class),
                    HealthPermission.getReadPermission(HeartRateRecord::class),
                    HealthPermission.getReadPermission(TotalCaloriesBurnedRecord::class),
                    HealthPermission.getReadPermission(DistanceRecord::class)
                )
            )
        }
    }
}