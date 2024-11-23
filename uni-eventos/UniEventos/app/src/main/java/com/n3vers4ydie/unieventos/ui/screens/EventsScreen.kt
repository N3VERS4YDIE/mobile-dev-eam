package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.n3vers4ydie.unieventos.Global
import com.n3vers4ydie.unieventos.R
import com.n3vers4ydie.unieventos.controllers.UserController
import com.n3vers4ydie.unieventos.controllers.eventController
import com.n3vers4ydie.unieventos.controllers.userController
import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.utils.loadImage
import com.n3vers4ydie.unieventos.utils.numberFormatter

@Composable
fun EventsScreen(onEventClick: (eventId: String) -> Unit, onAddEventClick: () -> Unit) {
    var events = remember { mutableStateListOf<EventModel>() }

    LaunchedEffect(Unit) {
        events.addAll(eventController.getAll())
    }

    Box(modifier = Modifier.padding(Global.innerPadding)) {
        EventGrid(events = events, onEventClick = { onEventClick(it.id) })

        if (UserController.loggedUser?.admin == true) {
            FloatingActionButton(
                onClick = { onAddEventClick() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Global.paddingValue),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Event",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        label = { Text(text = "Search Events") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun EventGrid(events: List<EventModel>, onEventClick: (EventModel) -> Unit) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        state = gridState,
        contentPadding = PaddingValues(Global.paddingValue),
        verticalArrangement = Arrangement.spacedBy(Global.paddingValue),
        horizontalArrangement = Arrangement.spacedBy(Global.paddingValue),
        modifier = Modifier.fillMaxSize()
    ) {
        items(events.size) { index ->
            EventCard(event = events[index], onClick = { onEventClick(events[index]) })
        }
    }
}

@Composable
fun EventCard(event: EventModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Column()
                {
                    Text(
                        text = event.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = event.location,
                        fontSize = 14.sp
                    )
                }
            }

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                model = loadImage(LocalContext.current, event.posterUrl),
                contentScale = ContentScale.Crop,
                contentDescription = "Event Poster",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_foreground)
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                val minPrice = event.localities.minOf { it.price }
                val maxPrice = event.localities.maxOf { it.price }
                val formattedMinPrice = numberFormatter.format(minPrice)
                val formattedMaxPrice = numberFormatter.format(maxPrice)
                var priceRange = (if (minPrice == maxPrice || maxPrice == 0.0) formattedMinPrice else "$formattedMinPrice - $formattedMaxPrice") + " COP"

                Text(
                    text = priceRange,
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}