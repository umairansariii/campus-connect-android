package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.viewmodel.StudentProfileViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun StudentProfileScreen(studentId: Int) {
    val viewModel: StudentProfileViewModel = hiltViewModel()
    val state = viewModel.state

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 12.dp),
        ) {
            Text(text = "Personal Information")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(12.dp)
                ) {
                    Row {
                        Text(text = "Name: ")
                        Text(
                            text = "${state.firstName} ${state.lastName}",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Email: ")
                        Text(
                            text = state.email,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Enrollment: ")
                        Text(
                            text = state.rollNo,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Birthday: ")
                        Text(
                            text = state.dob?.let { date ->
                                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                            } ?: "N/A",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Gender: ")
                        Text(
                            text = state.gender.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Faculty Information")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(12.dp)
                ) {
                    Row {
                        Text(text = "Department: ")
                        Text(
                            text = state.departmentName,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Campus: ")
                        Text(
                            text = state.campusName,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Semester No: ")
                        Text(
                            text = state.semester,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "CGPA: ")
                        Text(
                            text = state.cgpa,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Status: ")
                        Text(
                            text = state.status.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
            }
        }
    }
}