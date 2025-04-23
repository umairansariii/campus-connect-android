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
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.data.local.enums.EventType
import com.umairansariii.campusconnect.presentation.components.DateSelector
import com.umairansariii.campusconnect.presentation.components.ListSelector
import com.umairansariii.campusconnect.presentation.events.EventFormEvent
import com.umairansariii.campusconnect.viewmodel.EventViewModel

@Composable
fun EventDialog(universityId: Int) {
    val viewModel: EventViewModel = hiltViewModel()
    val state = viewModel.state

    val eventTypes = listOf(
        EventType.Alumni,
        EventType.Orientation,
        EventType.Social,
        EventType.Speaker,
        EventType.Sports,
        EventType.Workshop,
    )

    if (state.showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onEvent(EventFormEvent.DismissDialog())
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
                            text = "Update Event",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    } else {
                        Text(
                            text = "Create Event",
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
                                checked = state.eventIsActive,
                                onCheckedChange = {
                                    viewModel.onEvent(EventFormEvent.EventStateChanged(it))
                                },
                            )
                        }
                        OutlinedTextField(
                            value = state.eventTitle,
                            onValueChange = {
                                viewModel.onEvent(EventFormEvent.EventTitleChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Title") },
                            supportingText = {
                                if (state.eventTitleError != null) {
                                    Text(text = state.eventTitleError)
                                }
                            },
                            isError = state.eventTitleError != null,
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = state.eventDescription,
                            onValueChange = {
                                viewModel.onEvent(EventFormEvent.EventDescriptionChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Description") },
                            supportingText = {
                                if (state.eventDescriptionError != null) {
                                    Text(text = state.eventDescriptionError)
                                }
                            },
                            isError = state.eventDescriptionError != null,
                            minLines = 2,
                            maxLines = 3,
                        )
                        OutlinedTextField(
                            value = state.eventVenue,
                            onValueChange = {
                                viewModel.onEvent(EventFormEvent.EventVenueChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Venue") },
                            supportingText = {
                                if (state.eventVenueError != null) {
                                    Text(text = state.eventVenueError)
                                }
                            },
                            isError = state.eventVenueError != null,
                            singleLine = true,
                        )
                        DateSelector(
                            value = state.eventDate,
                            label = "Date",
                            onDateSelected = {
                                viewModel.onEvent(EventFormEvent.EventDateChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            supportingText = state.eventDateError,
                            isError = state.eventDateError != null,
                        )
                        ListSelector(
                            value = state.eventType,
                            label = "Type",
                            options = eventTypes,
                            itemToString = { it.name },
                            onItemSelected = {
                                viewModel.onEvent(EventFormEvent.EventTypeChanged(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            supportingText = state.eventTypeError,
                            isError = state.eventTypeError != null,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(EventFormEvent.Submit(universityId))
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