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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.events.ClubFormEvent
import com.umairansariii.campusconnect.viewmodel.ClubViewModel

@Composable
fun ClubDialog(universityId: Int) {
    val viewModel: ClubViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(ClubFormEvent.DismissDialog())
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
                    if (state.showDialogId !== null) {
                        Text(
                            text = "Update Club",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Club",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "Marked as active?")
                            Switch(
                                checked = state.clubIsActive,
                                onCheckedChange = {
                                    viewModel.onEvent(ClubFormEvent.ClubStateChanged(it))
                                },
                            )
                        }
                        OutlinedTextField(
                            value = state.clubTitle,
                            onValueChange = {
                                viewModel.onEvent(ClubFormEvent.ClubTitleChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (state.clubTitleError != null) {
                                    Text(text = state.clubTitleError)
                                }
                            },
                            isError = state.clubTitleError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.clubDescription,
                            onValueChange = {
                                viewModel.onEvent(ClubFormEvent.ClubDescriptionChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Description") },
                            supportingText = {
                                if (state.clubDescriptionError != null) {
                                    Text(text = state.clubDescriptionError)
                                }
                            },
                            isError = state.clubDescriptionError != null,
                            minLines = 2,
                            maxLines = 3,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(ClubFormEvent.Submit(universityId))
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            if (state.showDialogId !== null) {
                                Text(
                                    text = "Update",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            } else {
                                Text(
                                    text = "Create",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}