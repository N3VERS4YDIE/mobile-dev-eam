package com.n3vers4ydie.unieventos.models

import java.math.BigDecimal
import java.util.Date

class PurchaseOrderModel(
    val cartItems: List<CartItemModel>,
    val status: String,
    val total: BigDecimal,
    val date: Date
) : BaseModel()