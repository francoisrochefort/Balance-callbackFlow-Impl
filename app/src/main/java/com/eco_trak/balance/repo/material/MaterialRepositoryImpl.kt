package com.eco_trak.balance.repo.material

import com.eco_trak.balance.data.db.material.Material
import com.eco_trak.balance.data.db.material.MaterialDao

class MaterialRepositoryImpl(

    private val materialDao: MaterialDao

) : MaterialRepository {

    class MaterialAlreadyExists(val material: Material): Exception("Material ${material.name} already exists")

    override suspend fun getMaterialsFromRoom() = materialDao.getMaterials()
    override suspend fun getSearchFromRoom(text: String) = materialDao.getSearch(text)
    override suspend fun getMaterialFromRoom(id: Int) = materialDao.getMaterial(id)

    override suspend fun addMaterialToRoom(material: Material, replace: Boolean) : Long {
        val existing: Material? = materialDao.getMaterialByName(material.name)
        if (existing != null && !replace)
            throw MaterialAlreadyExists(material)
        return materialDao.addMaterial(material)
    }

    override suspend fun updateMaterialInRoom(material: Material, replace: Boolean) {
        val existing: Material? = materialDao.getMaterialByName(material.name)
        if ((existing != null) && (existing.id != material.id) && !replace)
            throw MaterialAlreadyExists(material)

        //materialDao.updateMaterial(material)
        materialDao.addMaterial(material)
    }
    override suspend fun deleteMaterialFromRoom(material: Material) = materialDao.deleteMaterial(material)
}
