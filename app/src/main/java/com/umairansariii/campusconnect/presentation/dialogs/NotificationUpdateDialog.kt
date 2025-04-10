package com.umairansariii.campusconnect.presentation.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.events.NotificationFormEvent
import com.umairansariii.campusconnect.viewmodel.NotificationViewModel

@Composable
fun NotificationUpdateDialog(universityId: Int) {
    val viewModel: NotificationViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showUpdateDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(NotificationFormEvent.DismissUpdateDialog())
            }
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = "Broadcast Notification",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Column {
                        OutlinedTextField(
                            value = state.notificationTitle,
                            onValueChange = {
                                viewModel.onEvent(NotificationFormEvent.NotificationTitleChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (state.notificationTitleError != null) {
                                    Text(text = state.notificationTitleError)
                                }
                            },
                            isError = state.notificationTitleError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.notificationDescription,
                            onValueChange = {
                                viewModel.onEvent(NotificationFormEvent.NotificationDescriptionChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Description") },
                            supportingText = {
                                if (state.notificationDescriptionError != null) {
                                    Text(text = state.notificationDescriptionError)
                                }
                            },
                            isError = state.notificationDescriptionError != null,
                            minLines = 3,
                            maxLines = 6,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(NotificationFormEvent.SubmitUpdate(universityId))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Broadcast", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}