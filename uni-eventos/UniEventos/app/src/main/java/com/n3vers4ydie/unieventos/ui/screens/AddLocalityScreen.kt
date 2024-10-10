package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.models.LocalityModel

@Composable
fun AddLocalityScreen(event: EventModel, onSaveLocality: (LocalityModel) -> Unit) {
    var localityName by remember { mutableStateOf("") }
    var localityPrice by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = localityName,
            onValueChange = { localityName = it },
            label = { Text("Locality Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = localityPrice,
            onValueChange = { localityPrice = it },
            label = { Text("Locality Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newLocality = LocalityModel(
                    name = localityName,
                    price = localityPrice.toBigDecimal()
                )
                onSaveLocality(newLocality)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Locality")
        }
    }
}
