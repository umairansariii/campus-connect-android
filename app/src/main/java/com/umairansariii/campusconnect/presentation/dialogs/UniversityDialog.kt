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
import com.umairansariii.campusconnect.presentation.components.ImagePicker
import com.umairansariii.campusconnect.presentation.events.UniversityFormEvent
import com.umairansariii.campusconnect.viewmodel.UniversityViewModel

@Composable
fun UniversityDialog(adminId: Int?) {
    val viewModel: UniversityViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(UniversityFormEvent.DismissDialog())
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
                            text = "Update University",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create University",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Column {
                        OutlinedTextField(
                            value = state.universityTitle,
                            onValueChange = {
                                viewModel.onEvent(UniversityFormEvent.UniversityTitleChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (state.universityTitleError != null) {
                                    Text(text = state.universityTitleError)
                                }
                            },
                            isError = state.universityTitleError != null,
                            singleLine = true,
                        )
                        ImagePicker(
                            onImageSelected = {
                                viewModel.onEvent(UniversityFormEvent.UniversityAvatarChanged(it))
                            }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(UniversityFormEvent.Submit(adminId))
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            if (state.showDialogId !== null) {
                                Text(text = "Update", style = MaterialTheme.typography.titleMedium)
                            } else {
                                Text(text = "Create", style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}