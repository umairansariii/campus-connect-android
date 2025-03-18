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
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.events.StudentFormEvent
import com.umairansariii.campusconnect.viewmodel.StudentViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun StudentViewDialog() {
    val viewModel: StudentViewModel = hiltViewModel()
    val state = viewModel.state

    if (state.showViewDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(StudentFormEvent.DismissViewDialog())
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Student Details",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        SuggestionChip(
                            onClick = { /* Do nothing */ },
                            label = {
                                Text("${state.status}", style = MaterialTheme.typography.bodyMedium)
                            }
                        )
                    }
                    Column {
                        Text(
                            text = "${state.firstName} ${state.lastName}",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = state.universityName,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Column {
                        Row {
                            Text(
                                text = "Department: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.departmentName,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Text(
                                text = "Campus: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.campusName,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Text(
                                text = "Enrollment: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.rollNo,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Text(
                                text = "Semester No: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.semester?.toString()?: "N/A",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Text(
                                text = "CGPA: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.cgpa?.toString()?: "N/A",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Column {
                        Row {
                            Text(
                                text = "Email: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.email,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Text(
                                text = "Birthdate: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = state.dob?.let { date ->
                                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                                } ?: "N/A",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Text(
                                text = "Gender: ",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = "${state.gender}",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(StudentFormEvent.DismissViewDialog())
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                        ) {
                            Text(text = "Close", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}