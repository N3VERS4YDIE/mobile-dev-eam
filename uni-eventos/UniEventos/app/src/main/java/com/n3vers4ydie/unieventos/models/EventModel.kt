package com.n3vers4ydie.unieventos.models

import java.util.Date

data class EventModel(
    val name: String,
    val date: Date,
    val location: String,
    val description: String,
    val posterUrl: String,
    val localities: MutableList<LocalityModel>
) : BaseModel()