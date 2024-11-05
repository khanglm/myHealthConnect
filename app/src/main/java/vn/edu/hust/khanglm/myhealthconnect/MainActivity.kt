package vn.edu.hust.khanglm.myhealthconnect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsRecord
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import vn.edu.hust.khanglm.core.database.datasource.HeartRateDatasource
import vn.edu.hust.khanglm.core.database.datasource.StepsCountDatasource
import vn.edu.hust.khanglm.myhealthconnect.core.data.SyncHealthDataRepository
import vn.edu.hust.khanglm.myhealthconnect.core.data.mapper.toEntity
import vn.edu.hust.khanglm.myhealthconnect.healthconnect.HealthConnectDatasource
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var syncHealthDataRepository: SyncHealthDataRepository

    @Inject
    lateinit var healthConnectDatasource: HealthConnectDatasource

    @Inject
    lateinit var stepsCountDatasource: StepsCountDatasource

    @Inject
    lateinit var heartRateDatasource: HeartRateDatasource

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            false
        }
        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
                Log.d("KhangLM2", "isGranted $isGranted")
                if (isGranted.all { it.value }) {
                    coroutineScope.launch {
                        val syncedStepsData = healthConnectDatasource.fetchStepsCountData(0L, 0L)
                        Log.d("KhangLm", "Steps synced = $syncedStepsData")
                        stepsCountDatasource.insertStepsData(syncedStepsData.map { it.toEntity() })
                    }
                    coroutineScope.launch {
                        val syncedHeartRateData = healthConnectDatasource.fetchHeartRateData(0L, 0L)
                        Log.d("KhangLm", "heart rate synced = $syncedHeartRateData")
                        heartRateDatasource.insertHeartRateRecord(syncedHeartRateData.map { it.toEntity() })
                    }

                }
            }
            LaunchedEffect(Unit) {
                permissionLauncher.launch(
                    arrayOf(
                        HealthPermission.getReadPermission(StepsRecord::class),
                        HealthPermission.getReadPermission(HeartRateRecord::class),
                        HealthPermission.getReadPermission(ActiveCaloriesBurnedRecord::class)
                    )
                )
            }
        }
    }
}