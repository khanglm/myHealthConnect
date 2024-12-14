package vn.edu.hust.khanglm.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.hust.khanglm.features.home.R
import vn.edu.hust.khanglm.core.ui.R as coreUIR

@Composable
internal fun StepsTrackingCard(
    totalSteps: Long,
    target: Long,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFFE4E4EB), shape = RoundedCornerShape(20.dp))
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.features_home_walk_title),
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(coreUIR.drawable.core_ui_icon_walker),
                contentDescription = "Icon Walker"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ProgressChart(
            targetValue = target.toFloat(),
            currentValue = totalSteps.toFloat()
        )
    }
}

@Composable
internal fun ProgressChart(
    targetValue: Float,
    currentValue: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {

        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = { currentValue/targetValue },
            strokeWidth = 10.dp,
            gapSize = 0.dp,
            trackColor = Color(0xFFFFFFFF),
            color = Color(0xFF2F80ED),
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$currentValue",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF161626),
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(R.string.features_home_steps_count_unit),
                fontWeight = FontWeight.Light,
                color = Color(0xFF79797F),
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview(name = "Steps Tracking Card")
@Composable
internal fun PreviewStepsTrackingCard() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            StepsTrackingCard(
                totalSteps = 1000,
                target = 5000
            )
        }
        item {
            StepsTrackingCard(
                totalSteps = 1000,
                target = 5000
            )
        }
        item {
            StepsTrackingCard(
                totalSteps = 1000,
                target = 5000
            )
        }
        item {
            StepsTrackingCard(
                totalSteps = 1000,
                target = 5000
            )
        }
    }
}

@Preview(name = "Chart")
@Composable
internal fun PreviewChart() {
    ProgressChart(
        targetValue = 100.0f,
        currentValue = 50.0f
    )
}
