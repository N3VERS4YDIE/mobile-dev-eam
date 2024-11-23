package com.n3vers4ydie.unieventos.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.n3vers4ydie.unieventos.models.BaseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

open class FirestoreRepository<T : BaseModel>(
    val collectionName: String, val clazz: KClass<T>
) : ViewModel() {
    private val db = Firebase.firestore
    val storage = MutableStateFlow(mutableMapOf<String, T>())

    init {
        viewModelScope.launch {
            getAll()
        }
    }

//    private fun fromDocument(data: Map<String, Any>): T {
//        val instance = clazz.createInstance()
//        clazz.declaredMemberProperties.forEach { prop ->
//            prop.isAccessible = true
//            val value = data[prop.name]
//            if (value != null) {
//                if (prop is KMutableProperty<*>) {
//                    val convertedValue = when (value) {
//                        is Timestamp -> value.toDate()
//                        else -> value
//                    }
//                    prop.setter.call(instance, convertedValue)
//                }
//            }
//        }
//        return instance
//    }

    private fun toDocument(entity: T): Map<String, Any> {
        return clazz.declaredMemberProperties.associate { prop ->
            prop.isAccessible = true
            prop.name to (prop.get(entity) ?: "")
        }
    }

    suspend fun getAll(): List<T> {
        val snapshot = db.collection(collectionName).get().await()
        val entities = snapshot.documents.mapNotNull {
            it.toObject(clazz.java)?.apply { id = it.id }
        }
        storage.value = entities.associateBy { it.id }.toMutableMap()
        return entities
    }

    suspend fun getById(id: String): T? {
        if (id.isNullOrEmpty()) {
            return null
        }
        val doc = db.collection(collectionName).document(id).get().await()
        return doc.toObject(clazz.java)?.apply { this.id = id }
    }

    fun save(entity: T) {
        viewModelScope.launch {
            val data = toDocument(entity)
            if (storage.value.containsKey(entity.id)) {
                db.collection(collectionName).document(entity.id).set(data).await()
            } else {
                val newDoc = db.collection(collectionName).add(data).await()
                entity.id = newDoc.id
            }
            storage.value[entity.id] = entity
        }
    }

    fun saveAll(entities: List<T>) {
        entities.forEach { save(it) }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            db.collection(collectionName).document(id).delete().await()
            storage.value.remove(id)
        }
    }

    fun applyToAll(action: suspend (T) -> Unit) {
        viewModelScope.launch {
            getAll().forEach { action(it) }
        }
    }

    fun applyById(id: String, action: suspend (T) -> Unit) {
        viewModelScope.launch {
            getById(id)?.let { action(it) }
        }
    }
}