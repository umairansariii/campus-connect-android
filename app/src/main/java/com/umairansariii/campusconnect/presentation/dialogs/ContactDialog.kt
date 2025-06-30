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
import com.umairansariii.campusconnect.data.local.enums.ContactType
import com.umairansariii.campusconnect.presentation.components.ListSelector
import com.umairansariii.campusconnect.presentation.events.ContactFormEvent
import com.umairansariii.campusconnect.viewmodel.ContactViewModel

@Composable
fun ContactDialog(universityId: Int) {
    val viewModel: ContactViewModel = hiltViewModel()
    val state = viewModel.state

    val contactTypes = listOf(
        ContactType.Administration,
        ContactType.Faculty,
        ContactType.Finance,
        ContactType.Support,
    )

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(ContactFormEvent.DismissDialog())
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
                            text = "Update Contact",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Contact",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Column {
                        OutlinedTextField(
                            value = state.contactName,
                            onValueChange = {
                                viewModel.onEvent(ContactFormEvent.ContactNameChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Name") },
                            supportingText = {
                                if (state.contactNameError != null) {
                                    Text(text = state.contactNameError)
                                }
                            },
                            isError = state.contactNameError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.contactPhone,
                            onValueChange = {
                                viewModel.onEvent(ContactFormEvent.ContactPhoneChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Phone") },
                            supportingText = {
                                if (state.contactPhoneError != null) {
                                    Text(text = state.contactPhoneError)
                                }
                            },
                            isError = state.contactPhoneError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.contactEmail,
                            onValueChange = {
                                viewModel.onEvent(ContactFormEvent.ContactEmailChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Email") },
                            supportingText = {
                                if (state.contactEmailError != null) {
                                    Text(text = state.contactEmailError)
                                }
                            },
                            isError = state.contactEmailError != null,
                            singleLine = true,
                        )
                        ListSelector(
                            value = state.contactType,
                            label = "Type",
                            options = contactTypes,
                            itemToString = { it.name },
                            onItemSelected = {
                                viewModel.onEvent(ContactFormEvent.ContactTypeChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            supportingText = state.contactTypeError,
                            isError = state.contactTypeError != null,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(ContactFormEvent.Submit(universityId))
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