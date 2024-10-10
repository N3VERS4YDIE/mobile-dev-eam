package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.controllers.cartItemController
import com.n3vers4ydie.unieventos.models.CartItemModel
import com.n3vers4ydie.unieventos.models.PurchaseOrderModel
import java.util.Date

@Composable
fun CartScreen(userId: Int, onCheckout: (PurchaseOrderModel) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val cartItems = cartItemController.getAll()

        Text(text = "Carrito de Compras", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems.size) { index ->
                CartItemRow(cartItem = cartItemController.getById(cartItems[index].id)!!)
            }
        }

        Button(
            onClick = {
                val purchaseOrder = PurchaseOrderModel(cartItems = cartItems, total = cartItems.sumOf { cartItem -> cartItem.getTotal() },
                    status = "Completed", date = Date())
                onCheckout(purchaseOrder)
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text(text = "Comprar")
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItemModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = cartItem.event.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Quantity: ${cartItem.quantity}", style = MaterialTheme.typography.bodyLarge)
        }
        Text(text = "$${cartItem.locality.price.multiply(cartItem.quantity.toBigDecimal())}", style = MaterialTheme.typography.headlineMedium)
    }
}