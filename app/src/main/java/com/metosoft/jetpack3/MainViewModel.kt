package com.metosoft.jetpack3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _pltFiles = MutableStateFlow<List<String>>(emptyList())
    val pltFiles: StateFlow<List<String>> get() = _pltFiles

    private val _selectedPltContent = MutableStateFlow<String?>(null)
    val selectedPltContent: StateFlow<String?> get() = _selectedPltContent

    fun setPltFiles(newFiles: List<String>) {
        _pltFiles.value = newFiles
    }

    fun selectPltFile(content: String?) {
        _selectedPltContent.value = content
    }
}
