package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.EventModel
import com.n3vers4ydie.unieventos.repositories.FirestoreRepository

val eventController = EventLocalController()

class EventLocalController : FirestoreRepository<EventModel>("events", EventModel::class) {
//    init {
//        for (i in 1..10) {
//            val localities = mutableListOf(
//                LocalityModel(name="Normal", price=Random.nextDouble(100_000.0, 500_000.0), capacity = Random.nextInt(50, 500)),
//                LocalityModel(name="VIP", price=Random.nextDouble(500_000.0, 1_000_000.0), capacity = Random.nextInt(50, 500))
//            )
//
//            val event = EventModel(
//                name = "Evento $i",
//                type = "Tipo $i",
//                city = "Ciudad $i",
//                location = "Ubicación $i",
//                description = "Descripción $i",
//                posterUrl = "https://picsum.photos/300/200/$i",
//                date = Date(),
//                localities = localities
//            )
//
//            save(event)
//        }
//    }
}
