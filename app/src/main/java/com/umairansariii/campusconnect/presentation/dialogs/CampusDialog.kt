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
import com.umairansariii.campusconnect.presentation.events.CampusFormEvent
import com.umairansariii.campusconnect.viewmodel.CampusViewModel

@Composable
fun CampusDialog(universityId: Int) {
    val viewModel: CampusViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(CampusFormEvent.DismissDialog())
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
                            text = "Update Campus",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Campus",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Column {
                        OutlinedTextField(
                            value = state.campusTitle,
                            onValueChange = {
                                viewModel.onEvent(CampusFormEvent.CampusTitleChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (state.campusTitleError != null) {
                                    Text(text = state.campusTitleError)
                                }
                            },
                            isError = state.campusTitleError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.campusCode,
                            onValueChange = {
                                viewModel.onEvent(CampusFormEvent.CampusCodeChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Campus Code") },
                            supportingText = {
                                if (state.campusCodeError != null) {
                                    Text(text = state.campusCodeError)
                                }
                            },
                            isError = state.campusCodeError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.campusLatitude,
                            onValueChange = {
                                viewModel.onEvent(CampusFormEvent.CampusLatitudeChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Latitude") },
                            supportingText = {
                                if (state.campusLatitudeError != null) {
                                    Text(text = state.campusLatitudeError)
                                }
                            },
                            isError = state.campusLatitudeError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.campusLongitude,
                            onValueChange = {
                                viewModel.onEvent(CampusFormEvent.CampusLongitudeChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Longitude") },
                            supportingText = {
                                if (state.campusLongitudeError != null) {
                                    Text(text = state.campusLongitudeError)
                                }
                            },
                            isError = state.campusLongitudeError != null,
                            singleLine = true,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(CampusFormEvent.Submit(universityId))
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