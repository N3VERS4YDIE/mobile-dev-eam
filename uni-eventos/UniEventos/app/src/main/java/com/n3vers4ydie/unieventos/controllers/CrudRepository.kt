package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.BaseModel

class CrudRepository<T : BaseModel> {

    val storage = mutableMapOf<Int, T>()

    fun getAll(): List<T> {
        return storage.values.toList()
    }

    fun getById(id: Int): T? {
        return storage[id]
    }

    fun save(entity: T) {
        val id = storage.size + 1
        entity.id = id
        storage[id] = entity
    }

    fun delete(id: Int): Boolean {
        return storage.remove(id) != null
    }
}