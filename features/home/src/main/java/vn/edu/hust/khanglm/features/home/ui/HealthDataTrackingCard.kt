package vn.edu.hust.khanglm.features.home.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.hust.khanglm.features.home.R
import vn.edu.hust.khanglm.myhealthconnect.common.formatToString

@Composable
internal fun HealthDataTrackingCard(
    value: Double,
    title: String,
    unit: String,
    @DrawableRes iconResId: Int,
    @DrawableRes imageGraph: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFFE4E4EB), shape = RoundedCornerShape(20.dp))
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 14.sp
            )

            Icon(
                modifier = Modifier,
                painter = painterResource(iconResId),
                contentDescription = "Icon Walker",
                tint = Color(0xFF5B5B5B)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(imageGraph),
            contentDescription = "Graph",
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(136f/80)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF2F80ED))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = value.formatToString(),
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF161626),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = unit,
            modifier = Modifier,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFF79797F)
        )
    }
}

@Preview(name = "Steps Tracking Card")
@Composable
internal fun PreviewBurnedCaloriesTrackingCard() {
    HealthDataTrackingCard(
        value = 165.0,
        title = "Water",
        unit = "Bottles",
        imageGraph = R.drawable.features_home_burned_calories_graph,
        iconResId = vn.edu.hust.khanglm.core.ui.R.drawable.core_ui_icon_walker
    )
}