package com.n3vers4ydie.unieventos.models

class UserModel (
    val idNumber: String,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    val isAdmin: Boolean
) : BaseModel()