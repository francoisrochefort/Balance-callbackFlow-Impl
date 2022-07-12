package com.eco_trak.balance.ui.materials.update_material

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
class UpdateMaterialViewModel @Inject constructor(
    private val repo: MaterialRepository
) : ViewModel() {

    var material by mutableStateOf(Material(""))
        private set

    private val _event = Channel<ListEvent<Material>>()
    val event = _event.receiveAsFlow()

    fun getMaterial(id: Int) {
        viewModelScope.launch {
            repo.getMaterialFromRoom(id).collect { response ->
                material = response
            }
        }
    }

    fun updateName(name: String) {
        material = material.copy(name = name)
    }

    fun updateMaterial(replace: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.updateMaterialInRoom(
                    material = material,
                    replace = replace
                )
                _event.send(ListEvent.OnUpdate(material))
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }
}