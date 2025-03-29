package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.data.local.dto.UserStudent
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import com.umairansariii.campusconnect.presentation.events.StudentFormEvent
import com.umairansariii.campusconnect.viewmodel.StudentViewModel

@Composable
fun StudentCard(student: UserStudent) {
    val viewModel: StudentViewModel = hiltViewModel()
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .padding(start = 18.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(text = "${student.firstName} ${student.lastName}")
                Text(
                    text = student.rollNo,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row {
                if (student.status == UserStatus.PENDING) {
                    SuggestionChip(
                        onClick = { /* Do nothing */ },
                        label = {
                            Text("${student.status}", style = MaterialTheme.typography.bodySmall)
                        }
                    )
                }
                Box {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "student-options-icon")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "View") },
                            onClick = {
                                viewModel.onEvent(StudentFormEvent.ShowViewDialog(id = student.id))
                            },
                        )
                        if (student.status != UserStatus.ENROLLED) {
                            DropdownMenuItem(
                                text = { Text(text = "Update") },
                                onClick = {
                                    viewModel.onEvent(StudentFormEvent.ShowUpdateDialog(id = student.id))
                                },
                            )
                        }
                        if (student.status == UserStatus.ENROLLED) {
                            DropdownMenuItem(
                                text = { Text(text = "Approve") },
                                onClick = {
                                    viewModel.onEvent(StudentFormEvent.ShowApproveDialog(id = student.id))
                                },
                            )
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}