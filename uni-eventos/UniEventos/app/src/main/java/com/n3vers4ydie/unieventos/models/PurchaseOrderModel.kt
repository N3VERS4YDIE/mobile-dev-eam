package com.n3vers4ydie.unieventos.models

import java.util.Date

class PurchaseOrderModel(
    override var id: String = "",
    var userId: String = "",
    var cartItems: List<CartItemModel> = listOf(),
    var status: String = "",
    var total: Double = 0.0,
    var date: Date = Date()
) : BaseModel(id)