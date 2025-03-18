package com.umairansariii.campusconnect.presentation.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.events.StudentFormEvent
import com.umairansariii.campusconnect.viewmodel.StudentViewModel

@Composable
fun StudentUpdateDialog() {
    val viewModel: StudentViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showUpdateDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(StudentFormEvent.DismissUpdateDialog())
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
                        text = "Student Academics",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Column {
                        OutlinedTextField(
                            value = state.cgpa,
                            onValueChange = {
                                viewModel.onEvent(StudentFormEvent.StudentCgpaChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Cgpa") },
                            supportingText = {
                                if (state.cgpaError != null) {
                                    Text(text = state.cgpaError)
                                }
                            },
                            isError = state.cgpaError != null,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                            ),
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.semester,
                            onValueChange = {
                                viewModel.onEvent(StudentFormEvent.StudentSemesterChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Semester") },
                            supportingText = {
                                if (state.semesterError != null) {
                                    Text(text = state.semesterError)
                                }
                            },
                            isError = state.semesterError != null,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                            singleLine = true,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(StudentFormEvent.SubmitUpdate(state.showUpdateDialogId?: -1))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Update", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}