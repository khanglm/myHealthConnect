package vn.edu.hust.khanglm.features.personal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun InfoSection(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isRequired: Boolean = false,
    isEnable: Boolean = true,
    readOnly: Boolean = !isEnable,
    isError: Boolean = false,
    onTextChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onTextChange,
        enabled = isEnable,
        readOnly = readOnly,
        isError = isError,
        label = {
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(title)
                        if (isRequired) {
                            append(" ")
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("*")
                            }
                        }
                    },
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        textStyle = MaterialTheme.typography.titleMedium,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.titleMedium.copy(color = Color.LightGray)
            )
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Black,
            disabledPlaceholderColor = Color.Black
        )
    )
}

@Composable
internal fun Avatar(
    modifier: Modifier = Modifier,
    userName: String
) {
    Box(
        modifier = modifier
            .size(140.dp)
            .background(Color(0xFF2F80ED), CircleShape)
            .clip(CircleShape)
    ) {
        Text(
            text = userName.firstOrNull()?.toString().orEmpty(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = 70.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(name = "InfoSection")
@Composable
private fun PreviewInfoSection() {
    InfoSection(
        modifier = Modifier.background(Color.White),
        title = "User Name",
        value = "Le Minh Khang",
        isRequired = true
    )
}

@Preview(name = "Avatar")
@Composable
private fun PreviewAvatar() {
    Avatar(
        userName = "Le Minh Khang"
    )
}