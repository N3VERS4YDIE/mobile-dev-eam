package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.Global
import com.n3vers4ydie.unieventos.controllers.purchaseOrderController
import com.n3vers4ydie.unieventos.models.PurchaseOrderModel
import com.n3vers4ydie.unieventos.utils.dateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(userId: String) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(Global.paddingValue)
    ) {
        val purchaseOrders = remember { mutableStateListOf<PurchaseOrderModel>() }

        LaunchedEffect(userId) {
            purchaseOrders.addAll(purchaseOrderController.getByUserId(userId))
        }

        LazyColumn (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            items(purchaseOrders.size) { index ->
                PurchaseOrderRow(purchaseOrder = purchaseOrders[index])
            }
        }
    }
}

@Composable
fun PurchaseOrderRow(purchaseOrder: PurchaseOrderModel) {
    Column {
        Text(
            text = "ID: ${purchaseOrder.id}",
        )
        Text(
            text = "Estado: ${purchaseOrder.status}"
        )
        Text(
            text = "Fecha: ${dateTimeFormatter.format(purchaseOrder.date)}"
        )
        Text(
            text = "Total: \$${purchaseOrder.total}", style = MaterialTheme.typography.headlineMedium
        )

        Column {
            purchaseOrder.cartItems.forEach { cartItem ->
                Text(
                    text = ".".repeat(50)
                )

                Text(text = "Evento: ${cartItem.event.name}")
                Text(text = "Localidad: ${cartItem.locality.name}")
                Text(text = "Cantidad: ${cartItem.quantity}")
                Text(text = "Precio: ${cartItem.locality.price}")
                Text(text = "Sub Total: ${cartItem.getTotal()}")
            }

            Text(
                text = ".".repeat(50)
            )
        }


        Spacer(modifier = Modifier.height(Global.paddingValue*6))
    }
}
