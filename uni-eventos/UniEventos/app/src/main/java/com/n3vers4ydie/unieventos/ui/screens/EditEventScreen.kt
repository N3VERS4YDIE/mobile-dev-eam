package com.n3vers4ydie.unieventos.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.Global
import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.models.LocalityModel
import com.n3vers4ydie.unieventos.ui.components.DatePicker
import com.n3vers4ydie.unieventos.ui.components.TimePicker
import kotlinx.coroutines.launch

@Composable
fun EditEventScreen(
    event: EventModel,
    onSaveEvent: (EventModel) -> Unit,
    onDeleteEvent: () -> Unit,
    onDiscardChanges: () -> Unit
) {
    val isExistingEvent = !event.id.isNullOrEmpty()

    var eventName by remember { mutableStateOf(event.name) }
    var eventDate by remember { mutableStateOf(event.date) }
    var eventLocation by remember { mutableStateOf(event.location) }
    var eventDescription by remember { mutableStateOf(event.description) }
    var eventPosterUrl by remember { mutableStateOf(event.posterUrl) }

    var eventLocalities = remember { mutableStateListOf<LocalityModel>() }

    LaunchedEffect(event.localities) {
            eventLocalities.addAll(event.localities)
    }

    BackHandler {
        onDiscardChanges()
    }

    Box(modifier = Modifier.padding(Global.innerPadding)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Evento", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(value = eventName,
                onValueChange = { eventName = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DatePicker(value = eventDate,
                    label = "Fecha",
                    onDateSelected = { eventDate = it.time },
                    onDatePickerDialogInit = { it.datePicker.minDate = System.currentTimeMillis() },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                TimePicker(
                    value = eventDate,
                    label = "Hora",
                    onTimeSelected = { eventDate = it.time },
                    modifier = Modifier.weight(1f)
                )
            }
            OutlinedTextField(value = eventLocation,
                onValueChange = { eventLocation = it },
                label = { Text("Ubicación") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = eventDescription,
                onValueChange = { eventDescription = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = eventPosterUrl,
                onValueChange = { eventPosterUrl = it },
                label = { Text("URL Poster") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Localidades", style = MaterialTheme.typography.headlineMedium)

            Column(modifier = Modifier.fillMaxWidth()) {
                eventLocalities.forEachIndexed { index, locality ->
                    LocalityItem(locality = locality, onUpdateLocality = { updatedLocality ->
                        eventLocalities[index] = updatedLocality
                    })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { eventLocalities.add(LocalityModel(name = "", price = 0.0, capacity = 0)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir Nueva Localidad")
            }
        }

        val coroutineScope = rememberCoroutineScope()

        FloatingActionButton(

            onClick = {
                coroutineScope.launch {
                    val updatedEvent = EventModel(
                        id = event.id,
                        name = eventName,
                        description = eventDescription,
                        type = "",
                        city = "",
                        location = eventLocation,
                        posterUrl = eventPosterUrl,
                        date = eventDate,
                        localities = eventLocalities
                    )
                    onSaveEvent(updatedEvent)
                }
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Global.paddingValue), containerColor = Color.Green
        ) {
            Icon(Icons.Default.Check, tint = Color.Black, contentDescription = "Save Event")
        }

        if (isExistingEvent) {
            FloatingActionButton(
                onClick = onDeleteEvent,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(Global.paddingValue),
                containerColor = Color.Red
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Event")
            }
        }

        FloatingActionButton(
            onClick = onDiscardChanges,
            modifier = Modifier
                .align(if (isExistingEvent) Alignment.BottomCenter else Alignment.BottomStart)
                .padding(Global.paddingValue),
        ) {
            Icon(Icons.Default.Cancel, contentDescription = "Discard Changes")
        }
    }
}

@Composable
fun LocalityItem(
    locality: LocalityModel, onUpdateLocality: (LocalityModel) -> Unit
) {
    var name by remember { mutableStateOf(locality.name) }
    var price by remember { mutableStateOf(locality.price.toString()) }
    var capacity by remember { mutableStateOf(locality.capacity.toString()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Global.paddingValue),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(value = name, onValueChange = {
            name = it
            onUpdateLocality(locality.copy(name = it))
        }, label = { Text("Nombre") }, modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(value = price, onValueChange = {
            price = it
            onUpdateLocality(locality.copy(price = it.toDoubleOrNull() ?: 0.0))
        }, label = { Text("Precio") }, modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(value = capacity, onValueChange = {
            capacity = it
            onUpdateLocality(locality.copy(capacity = it.toIntOrNull() ?: 0))
        }, label = { Text("Capacidad") }, modifier = Modifier.weight(1f)
        )
    }
}