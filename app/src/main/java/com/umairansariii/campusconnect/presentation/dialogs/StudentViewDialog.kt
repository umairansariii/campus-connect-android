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

@Composable
fun StudentViewDialog() {

    if (false) {
        Dialog(
            onDismissRequest = { /* Handle dismiss */ }
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
                            onClick = { /* Handle click */ },
                            label = {
                                Text("Pending", style = MaterialTheme.typography.bodyMedium)
                            }
                        )
                    }
                    Column {
                        Text(
                            text = "Muhammad Umair",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "Dawood University of Technology and Engineering",
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
                                text = "Computer Science",
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
                                text = "North Nazimabad Campus",
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
                                text = "BC210402929",
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
                                text = "7",
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
                                text = "2.82",
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
                                text = "umairansari.work@gmail.com",
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
                                text = "06/02/2000",
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
                                text = "Male",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.fillMaxWidth().height(45.dp)
                        ) {
                            Text(text = "Close", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}