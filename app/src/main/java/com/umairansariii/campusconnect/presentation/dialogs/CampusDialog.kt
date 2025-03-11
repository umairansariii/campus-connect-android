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

@Composable
fun CampusDialog() {

    if (false) {
        Dialog(
            onDismissRequest = { /**/ }
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
                        text = "Create Campus",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Column {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Handle change */ },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = { /* Handle error */ },
                            isError = false,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Handle change */ },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Campus Code") },
                            supportingText = { /* Handle error */ },
                            isError = false,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Handle change */ },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Latitude") },
                            supportingText = { /* Handle error */ },
                            isError = false,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Handle change */ },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Longitude") },
                            supportingText = { /* Handle error */ },
                            isError = false,
                            singleLine = true,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            Text(text = "Create", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}