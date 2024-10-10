package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.controllers.purchaseOrderController
import com.n3vers4ydie.unieventos.models.PurchaseOrderModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(userId: Int) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val purchaseOrders = purchaseOrderController.getAll()

        Text(
            text = "HIstorial de compras",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(purchaseOrders.size) { index ->
                PurchaseOrderRow(purchaseOrder = purchaseOrders[index])
            }
        }
    }
}

@Composable
fun PurchaseOrderRow(purchaseOrder: PurchaseOrderModel) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Order ID: ${purchaseOrder.id}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Status: ${purchaseOrder.status}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Date: ${dateFormat.format(purchaseOrder.date)}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Text(
            text = "$${purchaseOrder.total}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
