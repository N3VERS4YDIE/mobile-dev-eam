package com.n3vers4ydie.unieventos.repositories

import androidx.lifecycle.ViewModel
import com.n3vers4ydie.unieventos.models.BaseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class LocalRepository<T : BaseModel> : ViewModel() {
    private val _storage = MutableStateFlow(mutableMapOf<String, T>())
    val storage = _storage.asStateFlow()

    fun getAll(): List<T> {
        return storage.value.values.toList()
    }

    fun getById(id: String): T? {
        return storage.value[id]
    }

    fun save(entity: T) {
        if (getById(entity.id) != null) {
            storage.value[entity.id] = entity
            return
        }

        val id = (storage.value.size + 1).toString()
        entity.id = id
        storage.value[id] = entity
    }

    fun saveAll(entities: List<T>) {
        entities.forEach { save(it) }
    }

    fun delete(id: String): Boolean {
        return storage.value.remove(id) != null
    }

    fun deleteAll() {
        storage.value.clear()
    }
}