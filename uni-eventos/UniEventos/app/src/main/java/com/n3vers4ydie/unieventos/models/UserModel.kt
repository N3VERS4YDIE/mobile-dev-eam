package com.n3vers4ydie.unieventos.models

class UserModel(
    override var id: String = "",
    var dni: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var phone: String = "",
    var address: String = "",
    var admin: Boolean = false
) : BaseModel(id)