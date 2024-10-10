package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.UserModel

val userController = UserController()
var loggedUser: UserModel? = null

class UserController : BaseController<UserModel> {
    constructor() {
        val admin = UserModel(
            idNumber = "123456789",
            name = "Admin",
            email = "admin",
            password = "admin",
            phone = "123456789",
            address = "Admin Address",
            isAdmin = true
        )
        save(admin)

        val user = UserModel(
            idNumber = "987654321",
            name = "User",
            email = "user",
            password = "user",
            phone = "987654321",
            address = "User Address",
            isAdmin = false
        )
        save(user)
    }
}