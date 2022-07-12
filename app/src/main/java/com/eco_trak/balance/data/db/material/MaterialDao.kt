package com.eco_trak.balance.data.db.material

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDao {
    @Query("SELECT * FROM materials ORDER BY name ASC")
    fun getMaterials(): Flow<List<Material>>

    @Query("SELECT * FROM materials WHERE name LIKE :text || '%' ORDER BY name ASC")
    fun getSearch(text: String): Flow<List<Material>>

    @Query("SELECT * FROM materials WHERE id = :id")
    fun getMaterial(id: Int): Flow<Material>

    @Query("SELECT * FROM materials WHERE name = :name")
    fun getMaterialByName(name: String): Material?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMaterial(material: Material) : Long

    @Update
    fun updateMaterial(material: Material)

    @Delete
    fun deleteMaterial(material: Material)
}

