package com.umairansariii.campusconnect.presentation.events

import android.net.Uri
import com.umairansariii.campusconnect.data.local.enums.EventType
import java.util.Date

sealed interface EventFormEvent {
    data class ShowDialog(val id: Int?): EventFormEvent
    class DismissDialog: EventFormEvent
    data class EventTitleChanged(val eventTitle: String): EventFormEvent
    data class EventDescriptionChanged(val eventDescription: String): EventFormEvent
    data class EventVenueChanged(val eventVenue: String): EventFormEvent
    data class EventDateChanged(val eventDate: Date): EventFormEvent
    data class EventTypeChanged(val eventType: EventType): EventFormEvent
    data class EventStateChanged(val eventIsActive: Boolean): EventFormEvent
    data class EventBannerChanged(val eventBanner: Uri?): EventFormEvent
    data class EventQueryChanged(val eventQuery: String): EventFormEvent
    data class Submit(val universityId: Int): EventFormEvent
}