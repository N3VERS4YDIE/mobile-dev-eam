package com.n3vers4ydie.unieventos.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.n3vers4ydie.unieventos.utils.dateFormatter
import java.util.Calendar
import java.util.Date

@Composable
fun DatePicker(
    value: Date,
    label: String = "Date",
    onDateSelected: (Calendar) -> Unit,
    onDatePickerDialogInit: (DatePickerDialog) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.time = value
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )

    onDatePickerDialogInit(datePickerDialog)

    OutlinedTextField(value = dateFormatter.format(value),
        onValueChange = { },
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(
                    imageVector = Icons.Rounded.DateRange, contentDescription = "Seleccionar Fecha"
                )
            }
        },
        readOnly = true,
        modifier = modifier
    )
}