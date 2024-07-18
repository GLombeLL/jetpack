package com.example.jetpack3

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.metosoft.jetpack3.PltFileViewModel
import java.io.File

class MainActivity : ComponentActivity() {

    private val viewModel: PltFileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(viewModel)

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    viewModel.loadPltFiles()
                } else {
                    "SD karttan okuma izni verilmedi"

                }
            }



            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                viewModel.loadPltFiles()
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }


    class PltFileViewModel : ViewModel() {
        fun loadPltFiles() {
            TODO("Not yet implemented")
        }

        // MutableState veya LiveData kullanılabilir
        val message = mutableStateOf("Initial Message")
        val fileContents = mutableStateOf("Initial File Contents")
    }

    @Composable
    fun MyApp(viewModel: PltFileViewModel) {
        val message by remember { viewModel.message }
        val fileContents by remember { viewModel.fileContents }


        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                fileContents.forEach {
                    Text(
                        text = fileContents,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    fun readFileContent(file: File): String {
        return file.readText()
    }
}