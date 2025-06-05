package com.example.biblivent.model.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookViewModel : ViewModel() {
    private val _selectedTypes = MutableStateFlow<List<String>>(emptyList())
    val selectedTypes: StateFlow<List<String>> = _selectedTypes

    private val _selectedStyles = MutableStateFlow<List<String>>(emptyList())
    val selectedStyles: StateFlow<List<String>> = _selectedStyles

    private val _selectedPublics = MutableStateFlow<List<String>>(emptyList())
    val selectedPublics: StateFlow<List<String>> = _selectedPublics

    fun updateSelections(type: List<String>, style: List<String>, public: List<String>) {
        _selectedTypes.value = type
        _selectedStyles.value = style
        _selectedPublics.value = public
    }
}
