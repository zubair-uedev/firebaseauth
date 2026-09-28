package com.example.firebaseauth.shared.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.SemanticsProperties.Heading
import androidx.compose.ui.text.style.LineBreak.Companion.Heading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    actions: @Composable (() -> Unit)
) {
    TopAppBar(
        title = {
            Heading(text = title)
        },
        actions = {
            actions.invoke()
        }
    )
}