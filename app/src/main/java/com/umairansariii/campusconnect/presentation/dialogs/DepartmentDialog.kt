package com.umairansariii.campusconnect.presentation.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.umairansariii.campusconnect.presentation.events.DepartmentFormEvent
import com.umairansariii.campusconnect.viewmodel.DepartmentViewModel

@Composable
fun DepartmentDialog() {
    val viewModel: DepartmentViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(DepartmentFormEvent.DismissDialog())
            }
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    if (state.showDialogId !== null) {
                        Text(
                            text = "Update Department",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Department",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    OutlinedTextField(
                        value = state.departmentTitle,
                        onValueChange = {
                            viewModel.onEvent(DepartmentFormEvent.DepartmentTitleChanged(it))
                        },
                        label = { Text(text = "Title") },
                        supportingText = {
                            if (state.departmentTitleError != null) {
                                Text(text = state.departmentTitleError)
                            }
                        },
                        isError = state.departmentTitleError != null,
                        singleLine = true,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(DepartmentFormEvent.Submit(universityId = 1))
                            }
                        ) {
                            if (state.showDialogId !== null) {
                                Text(text = "Update")
                            } else {
                                Text(text = "Create")
                            }
                        }
                    }
                }
            }
        }
    }
}