package com.metosoft.jetpack3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.metosoft.jetpack3.ui.theme.Jetpack3Theme
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack3Theme {
                Surface(color = MaterialTheme.colors.background) {
                    PltScreen(viewModel)
                }
            }
        }

        // Load PLT files from assets directory
        val pltFiles = listOf(
            loadPltFileFromAssets("F1ATIH000.PLT"),
            loadPltFileFromAssets("GRACE EVEN TANK TOP S3M3 43 KAT.PLT"),
            loadPltFileFromAssets("HP-38614-38.PLT"),
            loadPltFileFromAssets("HP-ARCHANA-36.PLT")

        )
        viewModel.setPltFiles(pltFiles)
    }

    private fun loadPltFileFromAssets(fileName: String): String {
        val content = StringBuilder()
        val reader = BufferedReader(InputStreamReader(assets.open(fileName)))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            content.append(line).append("\n")
        }
        reader.close()
        return content.toString()
    }
}

@Composable
fun PltScreen(viewModel: MainViewModel) {
    val pltFiles by viewModel.pltFiles.collectAsState()
    val selectedPltContent by viewModel.selectedPltContent.collectAsState()

    val itemsPerPage = 4
    var currentPage by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val currentItems = pltFiles.chunked(itemsPerPage).getOrElse(currentPage) { emptyList() }

        LazyColumn {
            itemsIndexed(currentItems) { index, fileContent ->
                PltItem(fileContent = fileContent, index = index + 1, onClick = {
                    viewModel.selectPltFile(fileContent)
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (currentPage > 0) {
                Button(onClick = { currentPage-- }) {
                    Text("Previous")
                }
            }
            if ((currentPage + 1) * itemsPerPage < pltFiles.size) {
                Button(onClick = { currentPage++ }) {
                    Text("Next")
                }
            }
        }

        selectedPltContent?.let { content ->
            AlertDialog(
                onDismissRequest = { viewModel.selectPltFile(null) },
                title = { Text("PLT File Content") },
                text = { Text(content) },
                confirmButton = {
                    Button(onClick = { viewModel.selectPltFile(null) }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

@Composable
fun PltItem(fileContent: String, index: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color.LightGray)
            .padding(16.dp)
    ) {
        Button(onClick = onClick) {
            Text(text = "Seç")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "Çizim: $index", style = MaterialTheme.typography.h6)
    }
}