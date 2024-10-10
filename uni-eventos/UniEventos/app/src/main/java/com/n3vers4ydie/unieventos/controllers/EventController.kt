package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.models.LocalityModel
import java.util.Date
import kotlin.random.Random

val eventController = EventController()

class EventController : BaseController<EventModel> {
    constructor() {
        for (i in 1..10) {
            val localities = mutableListOf(
                LocalityModel("VIP", Random.nextInt(100_000, 1_000_000).toBigDecimal()),
                LocalityModel("Normal", Random.nextInt(50_000, 500_000).toBigDecimal())
            )

            for (locality in localities) {
                localityController.save(locality)
            }

            val event = EventModel(
                name = "Evento $i",
                date = Date(),
                location = "Ubicación $i",
                description = "Descripción $i",
                posterUrl = "https://picsum.photos/300/200/$i",
                localities = localities
            )

            save(event)
        }
    }
}
