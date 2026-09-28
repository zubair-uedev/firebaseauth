package com.example.firebaseauth.shared.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.Heading
import androidx.compose.ui.text.style.LineBreak.Companion.Heading
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppDialog(
    text: String,
    title: String,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit),
) {
    AlertDialog(
        title = {
            Heading(text = title)
        },
        text = {
            TextView(text = text)
        },
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    )
}
@Preview(showBackground = true)
@Composable
fun AppAlertDialog() {
    AppAlertDialog()
}
@Composable
fun AppAlertDialog(
    title: String,
    text: String,
    confirmText: String,
    onConfirm: () -> Unit,
    dismissText: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppDialog(
        title = title,
        text = text,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismissText)
            }
        },
        onDismissRequest = onDismiss,
        modifier = modifier
    )
}