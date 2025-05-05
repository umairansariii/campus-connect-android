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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DiscussionDialog(universityId: Int) {

    if (false) {
        Dialog(
            onDismissRequest = {
                /* Handle dismiss */
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
                    if (false) {
                        Text(
                            text = "Update Discussion",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Discussion",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "Marked as active?")
                            Switch(
                                checked = false,
                                onCheckedChange = {
                                    /* Handle change */
                                },
                            )
                        }
                        OutlinedTextField(
                            value = "",
                            onValueChange = {
                                /* Handle change */
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (false) {
                                    Text(text = "")
                                }
                            },
                            isError = false,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = {
                                /* Handle change */
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Description") },
                            supportingText = {
                                if (false) {
                                    Text(text = "")
                                }
                            },
                            isError = false,
                            minLines = 2,
                            maxLines = 3,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                /* Handle click */
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            if (false) {
                                Text(
                                    text = "Update",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            } else {
                                Text(
                                    text = "Create",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}