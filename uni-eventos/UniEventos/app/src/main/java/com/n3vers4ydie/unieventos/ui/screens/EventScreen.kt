package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.n3vers4ydie.unieventos.Global
import com.n3vers4ydie.unieventos.R
import com.n3vers4ydie.unieventos.controllers.UserController
import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.models.LocalityModel
import com.n3vers4ydie.unieventos.ui.components.Dropdown
import com.n3vers4ydie.unieventos.utils.dateFormatter
import com.n3vers4ydie.unieventos.utils.dateTimeFormatter
import com.n3vers4ydie.unieventos.utils.loadImage
import com.n3vers4ydie.unieventos.utils.timeFormatter
import kotlin.math.abs

@Composable
fun EventScreen(
    event: EventModel,
    onAddToCartClick: (locality: LocalityModel, quantity: Int) -> Unit,
    onEditEventClick: () -> Unit,
    onDeleteEventClick: () -> Unit
) {
    var quantity by remember { mutableStateOf("1") }
    var locality by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(Global.paddingValue),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                model = loadImage(LocalContext.current, event.posterUrl),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_foreground)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = event.name, style = MaterialTheme.typography.headlineLarge)
            Text(text = event.description)

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(text = "Tipo: ${event.type}")
                Text(text = "Ciudad: ${event.city}")
                Text(text = "Ubicación: ${event.location}")
                Text(text = "Fecha: ${dateFormatter.format(event.date)}")
                Text(text = "Hora: ${timeFormatter.format(event.date)}")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = quantity, onValueChange = {
                quantity = it
            }, label = { Text("Cantidad de entradas") }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ), modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Dropdown(options = event.localities.map { it.name }, onOptionSelected = {
                locality = it
            }, label = "Localidad"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onAddToCartClick(
                        event.localities.find { it.name == locality }!!, abs(quantity.toIntOrNull() ?: 0)
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al carrito \$${
                    quantity.toIntOrNull()
                        ?.let { event.localities.find { it.name == locality }?.price?.times(it) } ?: 0
                } COP")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (UserController.loggedUser?.admin == true) {
            FloatingActionButton(
                onClick = { onEditEventClick() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit, contentDescription = "Edit Event", tint = Color.White
                )
            }

            FloatingActionButton(
                onClick = { onDeleteEventClick() },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.error
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = "Delete Event", tint = Color.White
                )
            }
        }
    }

}
