package com.n3vers4ydie.unieventos.utils

import android.icu.text.NumberFormat
import java.math.BigDecimal
import java.util.Locale

fun formatBigDecimal(value: BigDecimal): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    return formatter.format(value)
}