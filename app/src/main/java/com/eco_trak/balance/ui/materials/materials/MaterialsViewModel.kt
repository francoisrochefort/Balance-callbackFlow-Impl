package com.eco_trak.balance.ui.materials.materials

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.material.Material
import com.eco_trak.balance.repo.material.MaterialRepository
import com.eco_trak.balance.ui.components.list.ListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialsViewModel @Inject constructor(
    private val repo: MaterialRepository
) : ViewModel() {

    var materials by mutableStateOf(emptyList<Material>())
    var search by mutableStateOf(emptyList<Material>())

    private var deleted: Material? = null

    private val _event = Channel<ListEvent<Material>>()
    val event = _event.receiveAsFlow()

    fun getMaterials() {
        viewModelScope.launch {
            repo.getMaterialsFromRoom().collect { response ->
                materials = response
            }
        }
    }

    fun getSearch(text: String) {
        viewModelScope.launch {
            repo.getSearchFromRoom(text).collect { response ->
                search = response
            }
        }
    }

    fun deleteMaterial(material: Material) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleted = material
                repo.deleteMaterialFromRoom(material)
                _event.send(ListEvent.OnDelete(material))
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }

    fun undoDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addMaterialToRoom(material = deleted!!, false)
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }
}
