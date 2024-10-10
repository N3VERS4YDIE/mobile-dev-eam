package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import java.util.Date

@Composable
fun ManageEventScreen(
    event: EventModel, onSaveEvent: (EventModel) -> Unit, onAddLocalityClick: (event: EventModel) -> Unit
) {
    var eventName by remember { mutableStateOf(event.name) }
    var eventDate by remember { mutableStateOf(event.date.toString()) }
    var eventLocation by remember { mutableStateOf(event.location) }
    var eventDescription by remember { mutableStateOf(event.description) }
    var eventPosterUrl by remember { mutableStateOf(event.posterUrl) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Evento", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("Event Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = eventDate,
            onValueChange = { eventDate = it },
            label = { Text("Event Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = eventLocation,
            onValueChange = { eventLocation = it },
            label = { Text("Event Location") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = eventDescription,
            onValueChange = { eventDescription = it },
            label = { Text("Event Description") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = eventPosterUrl,
            onValueChange = { eventPosterUrl = it },
            label = { Text("Poster URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updatedEvent = EventModel(
                    name = eventName,
                    date = Date(),
                    location = eventLocation,
                    description = eventDescription,
                    posterUrl = eventPosterUrl,
                    localities = event.localities
                )
                onSaveEvent(updatedEvent)
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Event")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Localidades", style = MaterialTheme.typography.headlineMedium)

        Column(modifier = Modifier.fillMaxWidth()) {
            event.localities.forEach { locality ->
                LocalityItem(locality = locality)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = { onAddLocalityClick(event) }, modifier = Modifier.fillMaxWidth()) {
            Text("Add New Locality")
        }
    }
}

@Composable
fun LocalityItem(locality: LocalityModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = locality.name, modifier = Modifier.weight(1f))
        Text(text = "$${locality.price}")
    }
}