package com.eco_trak.balance.repo.material

import com.eco_trak.balance.data.db.material.Material
import kotlinx.coroutines.flow.Flow

interface MaterialRepository {
    suspend fun getMaterialsFromRoom(): Flow<List<Material>>
    suspend fun getSearchFromRoom(text: String): Flow<List<Material>>
    suspend fun getMaterialFromRoom(id: Int): Flow<Material>

    suspend fun addMaterialToRoom(material: Material, replace: Boolean) : Long
    suspend fun updateMaterialInRoom(material: Material, replace: Boolean)
    suspend fun deleteMaterialFromRoom(material: Material)
}