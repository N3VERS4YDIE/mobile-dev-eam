package com.n3vers4ydie.unieventos.utils

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import java.util.Locale

val numberFormatter = NumberFormat.getNumberInstance(Locale.US)
val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
val dateTimeFormatter = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault())
