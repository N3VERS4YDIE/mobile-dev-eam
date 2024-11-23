package com.n3vers4ydie.unieventos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Global {
    companion object {
        val paddingValue: Dp = 8.dp
        var innerPadding: PaddingValues = PaddingValues(
            all = 0.dp
        )
    }
}