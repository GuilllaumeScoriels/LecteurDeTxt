package com.example.lecturemotparmotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.a18.FullscreenManager
import com.example.a18.LectureViewModelFactory
import com.example.a18.TextFileImporter
import com.example.a18.ui.theme.A18Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private lateinit var fullscreenManager: FullscreenManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        fullscreenManager = FullscreenManager(this)

        // 1) Créer l’importer avec un Context (l’Activity convient)
        val importer = TextFileImporter(this)
        // 2) Passer l’importer à la factory
        val viewModelFactory = LectureViewModelFactory(importer)
        // 3) Obtenir le VM
        val viewModel: LectureViewModel =
            ViewModelProvider(this, viewModelFactory)[LectureViewModel::class.java]

        setContent {
            A18Theme {
                // ⚠️ On réutilise l’instance Activity, pas de nouveau remember { FullscreenManager(...) }
                LectureMotParMotScreen(
                    vm = viewModel, // injecté proprement
                    fullscreenManager = fullscreenManager // injecté ou instancié selon ton architecture
                )
            }
        }
    }
}

@Composable
fun LectureMotParMotApp() {
    var inputText by remember { mutableStateOf("") }
    var currentWordIndex by remember { mutableStateOf(-1) }
    val words = remember(inputText) { inputText.split(" ").filter { it.isNotBlank() } }
    val coroutineScope = rememberCoroutineScope()
    var isReading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Entrez votre texte") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = !isReading
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = if (currentWordIndex in words.indices) words[currentWordIndex] else "",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = {
                if (!isReading && words.isNotEmpty()) {
                    isReading = true
                    coroutineScope.launch {
                        for (i in words.indices) {
                            currentWordIndex = i
                            delay(500)
                        }
                        currentWordIndex = -1
                        isReading = false
                    }
                }
            },
            enabled = !isReading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lancer la lecture")
        }
    }
}
