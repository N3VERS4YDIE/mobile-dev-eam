package com.n3vers4ydie.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.n3vers4ydie.unieventos.Global
import com.n3vers4ydie.unieventos.controllers.cartItemController
import com.n3vers4ydie.unieventos.models.CartItemModel
import com.n3vers4ydie.unieventos.models.PurchaseOrderModel
import java.util.Date

@Composable
fun CartScreen(userId: String, onCheckout: (PurchaseOrderModel) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(Global.paddingValue)
    ) {
        var cartItems = remember { mutableStateListOf<CartItemModel>() }

        LaunchedEffect(userId) {
            cartItems.addAll(cartItemController.getByUserId(userId))
        }

        LazyColumn {
            items(cartItems.size) {
                CartItemRow(cartItem = cartItems[it])
            }
        }

        Button(
            onClick = {
                val purchaseOrder = PurchaseOrderModel(
                    userId = userId,
                    cartItems = cartItems,
                    total = cartItems.sumOf { cartItem -> cartItem.getTotal() },
                    status = "Completado",
                    date = Date()
                )
                onCheckout(purchaseOrder)
            }, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Comprar \$${cartItems.sumOf { cartItem -> cartItem.getTotal() }} COP")
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItemModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Global.paddingValue)
            .background(MaterialTheme.colorScheme.surface)
            .padding(Global.paddingValue)
    ) {

        Column {
            Text(text = cartItem.event.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Localidad: ${cartItem.locality.name}")
            Text(text = "Cantidad: ${cartItem.quantity}")
            Text(text = "Precio: $${cartItem.locality.price}")
            Text(text = "Sub Total: $${cartItem.getTotal()}")
        }
        Text(text = "$${cartItem.locality.price * cartItem.quantity}", style = MaterialTheme.typography.headlineMedium)
    }
}