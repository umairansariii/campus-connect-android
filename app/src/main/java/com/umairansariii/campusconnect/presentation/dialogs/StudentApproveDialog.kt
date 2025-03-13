package com.umairansariii.campusconnect.presentation.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StudentApproveDialog() {

    if (false) {
        AlertDialog(
            icon = {
                Icon(Icons.Outlined.Verified, contentDescription = "student-approve-card-icon")
            },
            title = { Text(text = "Approve Student") },
            text = {
                Text(
                    text = "You've reviewed all the information provided by student and verified as correct.",
                    textAlign = TextAlign.Center,
                )
            },
            onDismissRequest = { /* Handle dismiss */ },
            confirmButton = {
                TextButton(
                    onClick = { /* Handle click */ }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { /* Handle click */ }
                ) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}