package com.umairansariii.campusconnect.presentation.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.events.StudentFormEvent
import com.umairansariii.campusconnect.viewmodel.StudentViewModel

@Composable
fun StudentApproveDialog() {
    val viewModel: StudentViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showApproveDialog) {
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
            onDismissRequest = {
                viewModel.onEvent(StudentFormEvent.DismissApproveDialog())
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(StudentFormEvent.SubmitApprove(state.showApproveDialogId?: -1))
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(StudentFormEvent.DismissApproveDialog())
                    }
                ) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}