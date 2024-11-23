package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.UserModel
import com.n3vers4ydie.unieventos.repositories.FirestoreRepository

val userController = UserController()

class UserController : FirestoreRepository<UserModel>("users", UserModel::class) {
    companion object {
        var loggedUser: UserModel? = null
    }
}