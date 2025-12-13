package com.example.lecturemotparmotapp

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedWord(current: String) {
    AnimatedContent(
        targetState = current,
        transitionSpec = {
            // Nouveau mot : entre par la droite
            // Ancien mot : sort par la gauche
            slideInHorizontally { fullWidth -> fullWidth } with
                    slideOutHorizontally { fullWidth -> -fullWidth }
        }
    ) { targetWord ->
        Text(
            text = targetWord,
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}
