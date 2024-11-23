package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.PurchaseOrderModel
import com.n3vers4ydie.unieventos.repositories.FirestoreRepository

val purchaseOrderController = PurchaseOrderController()

class PurchaseOrderController : FirestoreRepository<PurchaseOrderModel>("purchaseOrders", PurchaseOrderModel::class) {
    suspend fun getByUserId(userId: String) = getAll().filter { it.userId == userId }
}