package com.umairansariii.campusconnect.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.EventDao
import com.umairansariii.campusconnect.data.local.dto.EventUniversity
import com.umairansariii.campusconnect.data.local.entities.Event
import com.umairansariii.campusconnect.domain.libs.saveImageToInternalStorage
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.domain.usecase.ValidateNull
import com.umairansariii.campusconnect.presentation.events.EventFormEvent
import com.umairansariii.campusconnect.presentation.states.EventFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventDao: EventDao,
    private val application: Application,
): ViewModel() {
    private val validateEmpty = ValidateEmpty()
    private val validateNull = ValidateNull()
    var state by mutableStateOf(EventFormState())

    fun getEventsByUniversity(universityId: Int): Flow<List<Event>> {
        return eventDao.getEventsByUniversity(universityId, state.eventQuery)
    }

    fun getEventsByStudent(studentId: Int): Flow<List<EventUniversity>> {
        return eventDao.getEventsByStudent(studentId)
    }

    fun onEvent(event: EventFormEvent) {
        when(event) {
            is EventFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val eventData = eventDao.getEventById(event.id)
                        state = state.copy(
                            showDialog = true,
                            showDialogId = event.id,
                            eventTitle = eventData.title,
                            eventDescription = eventData.description,
                            eventVenue = eventData.venue,
                            eventDate = eventData.date,
                            eventType = eventData.type,
                            eventIsActive = eventData.isActive,
                            eventBannerUrl = eventData.bannerUrl.toUri(),
                        )
                    }
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is EventFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    eventTitle = "",
                    eventTitleError = null,
                    eventDescription = "",
                    eventDescriptionError = null,
                    eventVenue = "",
                    eventVenueError = null,
                    eventDate = null,
                    eventDateError = null,
                    eventType = null,
                    eventTypeError = null,
                    eventIsActive = true,
                    eventBannerUrl = null,
                )
            }

            is EventFormEvent.EventTitleChanged -> {
                state = state.copy(eventTitle = event.eventTitle, eventTitleError = null)
            }

            is EventFormEvent.EventDescriptionChanged -> {
                state = state.copy(eventDescription = event.eventDescription, eventDescriptionError = null)
            }

            is EventFormEvent.EventVenueChanged -> {
                state = state.copy(eventVenue = event.eventVenue, eventVenueError = null)
            }

            is EventFormEvent.EventDateChanged -> {
                state = state.copy(eventDate = event.eventDate, eventDateError = null)
            }

            is EventFormEvent.EventTypeChanged -> {
                state = state.copy(eventType = event.eventType, eventTypeError = null)
            }

            is EventFormEvent.EventStateChanged -> {
                state = state.copy(eventIsActive = event.eventIsActive)
            }

            is EventFormEvent.EventBannerChanged -> {
                state = state.copy(eventBannerUrl = event.eventBanner)
            }

            is EventFormEvent.EventQueryChanged -> {
                state = state.copy(eventQuery = event.eventQuery)
            }

            is EventFormEvent.Submit -> {
                submit(event.universityId)
            }
        }
    }

    private fun submit(universityId: Int) {
        val titleResult = validateEmpty.execute(value = state.eventTitle, fieldName = "Title")
        val descriptionResult = validateEmpty.execute(value = state.eventDescription, fieldName = "Description")
        val venueResult = validateEmpty.execute(value = state.eventVenue, fieldName = "Venue")
        val dateResult = validateNull.execute(value = state.eventDate, fieldName = "Date")
        val typeResult = validateNull.execute(value = state.eventType, fieldName = "Type")

        val hasError = listOf(
            titleResult,
            descriptionResult,
            venueResult,
            dateResult,
            typeResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                eventTitleError = titleResult.errorMessage,
                eventDescriptionError = descriptionResult.errorMessage,
                eventVenueError = venueResult.errorMessage,
                eventDateError = dateResult.errorMessage,
                eventTypeError = typeResult.errorMessage,
            )
            return
        }

        val imagePath = state.eventBannerUrl?.let { saveImageToInternalStorage(application, it) }

        viewModelScope.launch {
            if (state.showDialogId !== null) {
                eventDao.updateEvent(
                    Event(
                        id = state.showDialogId!!,
                        universityId = universityId,
                        title = state.eventTitle,
                        description = state.eventDescription,
                        venue = state.eventVenue,
                        date = state.eventDate!!,
                        type = state.eventType!!,
                        isActive = state.eventIsActive,
                        bannerUrl = imagePath?: state.eventBannerUrl.toString(),
                    )
                )
            } else {
                eventDao.insertEvent(
                    Event(
                        universityId = universityId,
                        title = state.eventTitle,
                        description = state.eventDescription,
                        venue = state.eventVenue,
                        date = state.eventDate!!,
                        type = state.eventType!!,
                        isActive = state.eventIsActive,
                        bannerUrl = imagePath?: "",
                    )
                )
            }
        }

        state = state.copy(
            showDialog = false,
            showDialogId = null,
            eventTitle = "",
            eventTitleError = null,
            eventDescription = "",
            eventDescriptionError = null,
            eventVenue = "",
            eventVenueError = null,
            eventDate = null,
            eventDateError = null,
            eventType = null,
            eventTypeError = null,
            eventIsActive = true,
            eventBannerUrl = null,
        )
    }
}