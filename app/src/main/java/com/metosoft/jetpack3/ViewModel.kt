package com.metosoft.jetpack3


import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Environment
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack3.readFileContent
import kotlinx.coroutines.launch
import java.io.File

private operator fun <T> Comparable<T>.invoke(value: Any): Any {

}

class PltFileViewModel(application: Application) : AndroidViewModel(application) {

    var pltFiles by mutableStateOf<List<File>>(emptyList())
    var message by mutableStateOf("PLT dosyaları burada listelenecek")
    var fileContents by mutableStateOf<List<String>>(emptyList())

    private val context = getApplication<Application>().applicationContext

    fun checkAndLoadFiles() {
        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            loadPltFiles()
        } else {
            message = "SD karttan okuma izni verilmedi"
        }
    }

    fun loadPltFiles() {
        viewModelScope.launch {
            val files = listPltFiles()
            pltFiles = files
            if (files.isNotEmpty()) {readFileContent(
                fileContents = files.map { readFileContent(it) }
                message = fileContents.joinToString("\n") { it }
                 else {
                message = "SD kartta .plt dosyası bulunamadı"
            }
        }
    }

  fun listPltFiles(): List<File> {
        val pltFiles = mutableListOf<File>()
        val externalStorageDir = Environment.getExternalStorageDirectory()
        val files = externalStorageDir.listFiles()
        files?.forEach { file ->
            if (file.extension == "plt") {
                pltFiles.add(file)
            }
        }
        return pltFiles
    }
}
