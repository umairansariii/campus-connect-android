package com.umairansariii.campusconnect.presentation.states

import com.umairansariii.campusconnect.data.local.enums.EventType
import java.util.Date

data class EventFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val eventTitle: String = "",
    val eventTitleError: String? = null,
    val eventDescription: String = "",
    val eventDescriptionError: String? = null,
    val eventVenue: String = "",
    val eventVenueError: String? = null,
    val eventDate: Date? = null,
    val eventDateError: String? = null,
    val eventType: EventType? = null,
    val eventTypeError: String? = null,
    val eventIsActive: Boolean = true,
)