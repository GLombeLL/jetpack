package com.metosoft.jetpack3

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import java.io.File

class MainActivity : ComponentActivity() {

    public val viewModel: PltFileViewModel by viewModels()

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
}


private class PltFileViewModel1 : ViewModel(){
    fun loadPltFiles() {
        TODO("Not yet implemented")
    }
    fun listPltFiles() {
        TODO("Not yet implemented")
    }
    fun readFileContent(file: File): String {
        return file.readText()
    }
    // MutableState veya LiveData kullanılabilir
    val message = mutableStateOf("Initial Message")
    val fileContents = mutableStateOf("Initial File Contents")

    constructor(parcel: Parcel) : this() {
    }

    constructor()


    companion object CREATOR : Parcelable.Creator<PltFileViewModel> {
        override fun createFromParcel(parcel: Parcel): PltFileViewModel1 {
            return PltFileViewModel1(parcel)
        }

        override fun newArray(size: Int): Array<PltFileViewModel?> {
            return arrayOfNulls(size)
        }
    }

}

@Composable
fun MyApp(viewModel: PltFileViewModel1) {
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

        }}}