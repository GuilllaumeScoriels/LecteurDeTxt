package com.example.a18

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.lecturemotparmotapp.LectureViewModel
import com.example.lecturemotparmotapp.WordStrip
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel


class FullScreenReadingScreen {
    @Composable
    fun Display(
        prevWord: String,
        currentWord: String,
        nextWord: String,
        onExit: () -> Unit,
    ) {
        val coroutineScope = rememberCoroutineScope()
        val vm: LectureViewModel = viewModel()
        WordStrip(
            prev = prevWord,
            current = currentWord,
            next = nextWord,
            isFullScreen = true,
            onExit = {
                coroutineScope.launch {
                    delay(30)
                    vm.setFullScreenMode(false)
                }
                vm.pauseReading()
            }
        )
    }
}
