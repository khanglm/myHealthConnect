package vn.edu.hust.khanglm.core.ui.datepicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import vn.edu.hust.khanglm.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerState(),
    onConfirmClicked: (Long) -> Unit,
    onDismissRequest: () -> Unit
) {
    DatePickerDialog(modifier = modifier, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        Text(text = stringResource(R.string.core_ui_ok_button),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable {
                    datePickerState.selectedDateMillis?.let { onConfirmClicked(it) }
                        ?: onDismissRequest()
                }
        )

    }, dismissButton = {
        Text(text = stringResource(R.string.core_ui_cancel_button),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable {
                    onDismissRequest()
                }
        )
    }) {
        DatePicker(
            state = datePickerState
        )
    }
}