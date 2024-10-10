package com.n3vers4ydie.unieventos.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(
    options: List<String>,
    selectedOptionIndex: Int = 0,  // Default index
    onOptionSelected: (String) -> Unit,  // Callback to return the selected index
    modifier: Modifier = Modifier,
    label: String = "Select an option"
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(label) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { /* No direct editing */ },
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),  // Used to anchor the dropdown to the text field
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            keyboardActions = KeyboardActions(onDone = { expanded = false })
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedText = option
                        expanded = false
                        onOptionSelected(options[index])
                    }
                )
            }
        }
    }
}