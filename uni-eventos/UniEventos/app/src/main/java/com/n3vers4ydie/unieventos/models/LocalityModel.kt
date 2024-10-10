package com.n3vers4ydie.unieventos.models

import java.math.BigDecimal

data class LocalityModel(
    val name: String,
    val price: BigDecimal
) : BaseModel()