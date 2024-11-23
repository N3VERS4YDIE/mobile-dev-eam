package com.n3vers4ydie.unieventos.models

data class CartItemModel(
    override var id: String = "",
    var userId: String = "",
    var event: EventModel = EventModel(),
    var locality: LocalityModel = LocalityModel(),
    var quantity: Int = 0,
) : BaseModel(id) {
    fun getTotal() = locality.price * quantity
}