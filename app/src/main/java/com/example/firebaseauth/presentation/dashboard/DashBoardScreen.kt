package com.example.firebaseauth.presentation.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DashBoardScreen(
    state: HomeState = HomeState(),
    onIntent: (HomeIntent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppTopBar(
            title = "Home",
            actions = {
                AppIconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    onClick = { onIntent(HomeIntent.LogoutRequested) },
                    contentDescription = "Logout",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TextWithTextAlign(
                text = "Welcome to\nHome Screen!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (state.isLogoutDialogVisible) {
        AppAlertDialog(
            title = "Logout",
            text = "Are you sure you want to log out?",
            confirmText = "Yes",
            onConfirm = { onIntent(HomeIntent.ConfirmLogout) },
            onDismiss = { onIntent(HomeIntent.DismissLogoutDialog) },
            dismissText = "Cancel"
        )
    }
}

