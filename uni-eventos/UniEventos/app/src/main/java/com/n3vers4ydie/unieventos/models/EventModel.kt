package com.n3vers4ydie.unieventos.models

import java.util.Date

data class EventModel(
    override var id: String = "",
    var name: String = "",
    var description: String = "",
    var type: String = "",
    var city: String = "",
    var location: String = "",
    var posterUrl: String = "",
    var date: Date = Date(),
    var localities: MutableList<LocalityModel> = mutableListOf()
) : BaseModel(id)