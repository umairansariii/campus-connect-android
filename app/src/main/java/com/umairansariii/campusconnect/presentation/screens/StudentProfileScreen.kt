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

@Composable
fun StudentProfileScreen(studentId: Int) {

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
                            text = "Muhammad Umair",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Email: ")
                        Text(
                            text = "umairansari.work@gmail.com",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Enrollment: ")
                        Text(
                            text = "BC210402929",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Birthday: ")
                        Text(
                            text = "02/06/2000",
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
                            text = "Computer Science",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Campus: ")
                        Text(
                            text = "Nazimabad Campus",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "Semester No: ")
                        Text(
                            text = "4",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Row {
                        Text(text = "CGPA: ")
                        Text(
                            text = "3.2",
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
            }
        }
    }
}