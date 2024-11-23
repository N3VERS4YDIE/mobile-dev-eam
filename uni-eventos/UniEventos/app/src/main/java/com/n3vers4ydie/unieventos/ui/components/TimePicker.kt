package com.n3vers4ydie.unieventos.ui.components

import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.widget.TimePicker
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.utils.timeFormatter
import java.util.*

@Composable
fun TimePicker(
    value: Date, label: String = "Time", onTimeSelected: (Calendar) -> Unit, modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val timePickerDialog = TimePickerDialog(
        context, { _: TimePicker, hourOfDay: Int, minute: Int ->
            calendar.time = value
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            onTimeSelected(calendar)
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false
    )

    OutlinedTextField(value = timeFormatter.format(value),
        onValueChange = { },
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = { timePickerDialog.show() }) {
                Icon(
                    imageVector = Icons.Default.AccessTime, contentDescription = "Seleccionar Hora"
                )
            }
        },
        readOnly = true,
        modifier = modifier
    )
}