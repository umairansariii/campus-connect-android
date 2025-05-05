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
import com.umairansariii.campusconnect.presentation.events.DiscussionFormEvent
import com.umairansariii.campusconnect.viewmodel.DiscussionViewModel

@Composable
fun DiscussionDialog(universityId: Int) {
    val viewModel: DiscussionViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(DiscussionFormEvent.DismissDialog())
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
                            text = "Update Discussion",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Discussion",
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
                                checked = state.discussionIsActive,
                                onCheckedChange = {
                                    viewModel.onEvent(DiscussionFormEvent.DiscussionStateChanged(it))
                                },
                            )
                        }
                        OutlinedTextField(
                            value = state.discussionTitle,
                            onValueChange = {
                                viewModel.onEvent(DiscussionFormEvent.DiscussionTitleChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (state.discussionTitleError != null) {
                                    Text(text = state.discussionTitleError)
                                }
                            },
                            isError = state.discussionTitleError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.discussionDescription,
                            onValueChange = {
                                viewModel.onEvent(DiscussionFormEvent.DiscussionDescriptionChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Description") },
                            supportingText = {
                                if (state.discussionTitleError != null) {
                                    Text(text = state.discussionTitleError)
                                }
                            },
                            isError = state.discussionTitleError != null,
                            minLines = 2,
                            maxLines = 3,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(DiscussionFormEvent.Submit(universityId))
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