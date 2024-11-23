package com.n3vers4ydie.unieventos.controllers

import androidx.lifecycle.viewModelScope
import com.n3vers4ydie.unieventos.models.CartItemModel
import com.n3vers4ydie.unieventos.repositories.FirestoreRepository
import kotlinx.coroutines.launch

val cartItemController = CartItemController()

class CartItemController : FirestoreRepository<CartItemModel>("cartItems", CartItemModel::class) {
    suspend fun getByUserId(userId: String) = getAll().filter { it.userId == userId }

    suspend fun getByEventId(eventId: String) = getAll().filter { it.event.id == eventId }

    suspend fun getByUserIdAndEventId(userId: String, eventId: String) =
        getAll().filter { it.userId == userId && it.event.id == eventId }
}