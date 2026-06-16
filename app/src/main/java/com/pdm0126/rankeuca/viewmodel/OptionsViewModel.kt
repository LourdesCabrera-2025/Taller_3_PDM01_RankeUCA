package com.pdm0126.rankeuca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm0126.rankeuca.Taller3RankeUca
import com.pdm0126.rankeuca.model.Option
import com.pdm0126.rankeuca.repository.OptionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class OptionsViewModel(
    private val optionRepository: OptionRepository
) : ViewModel() {

    val options: StateFlow<List<Option>> =
        optionRepository.getOptions()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun addOption(name: String, imageUrl: String) {
        viewModelScope.launch {
            optionRepository.addOption(Option(name = name, imageUrl = imageUrl))
        }
    }

    fun deleteOption(option: Option) {
        viewModelScope.launch {
            optionRepository.deleteOption(option)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as Taller3RankeUca
                OptionsViewModel(app.appProvider.provideOptionRepository())
            }
        }
    }
}