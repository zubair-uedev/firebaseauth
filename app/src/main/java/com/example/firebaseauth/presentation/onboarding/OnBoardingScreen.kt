package com.example.firebaseauth.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(onIntent = {})
}

@Composable
fun OnBoardingScreen(onIntent: (OnBoardingIntent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {onIntent(OnBoardingIntent.OnIntentsOnBoarding)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, bottom = 20.dp, end = 10.dp)
                ) {
                    Text(
                        text = "Go To Login Screen",
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }
    }
}