package com.n3vers4ydie.unieventos.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest

fun loadImage(context: Context, url: String): ImageRequest {
    return ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .build()
}