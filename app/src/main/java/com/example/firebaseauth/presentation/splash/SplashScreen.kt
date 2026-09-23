package com.example.firebaseauth.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

    SplashScreen(
        progress = 1f
    )
}

@Composable
fun SplashScreen(
    progress: Float
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 16.sp
        )
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 10.dp, bottom = 30.dp)
        )
    }
}