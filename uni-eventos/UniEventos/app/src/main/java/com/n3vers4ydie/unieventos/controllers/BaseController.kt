package com.n3vers4ydie.unieventos.controllers

import com.n3vers4ydie.unieventos.models.BaseModel

open class BaseController<T : BaseModel> {

    private val repository = CrudRepository<T>()

    fun getAll(): List<T> {
        return repository.getAll()
    }

    fun getById(id: Int): T? {
        return repository.getById(id)
    }

    fun save(entity: T) {
        repository.save(entity)
    }

    fun delete(id: Int): Boolean {
        return repository.delete(id)
    }
}