package com.n3vers4ydie.unieventos.models

import java.math.BigDecimal

data class CartItemModel (
    val event: EventModel,
    val locality: LocalityModel,
    val quantity: Int
): BaseModel() {
    fun getTotal(): BigDecimal {
        return locality.price.multiply(quantity.toBigDecimal())
    }
}