package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.NotificationDao
import com.umairansariii.campusconnect.data.local.entities.Notification
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.presentation.events.NotificationFormEvent
import com.umairansariii.campusconnect.presentation.states.NotificationFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationDao: NotificationDao
): ViewModel() {
    private val validateEmpty = ValidateEmpty()
    var state by mutableStateOf(NotificationFormState())

    fun getNotificationsByUniversity(universityId: Int): Flow<List<Notification>> {
        return notificationDao.getAllNotificationsByUniversity(universityId, state.notificationQuery)
    }

    fun getNotificationsByStudent(studentId: Int): Flow<List<Notification>> {
        return notificationDao.getNotificationsByStudent(studentId)
    }

    fun onEvent(event: NotificationFormEvent) {
        when(event) {
            is NotificationFormEvent.ShowViewDialog -> {
                viewModelScope.launch {
                    val notification = notificationDao.getNotificationById(event.id)
                    state = state.copy(
                        showViewDialog = true,
                        showViewDialogId = event.id,
                        createdAt = notification.createdAt,
                        notificationTitle = notification.title,
                        notificationDescription = notification.description,
                    )
                }
            }

            is NotificationFormEvent.DismissViewDialog -> {
                state = state.copy(
                    showViewDialog = false,
                    showViewDialogId = null,
                    createdAt = null,
                    notificationTitle = "",
                    notificationDescription = "",
                )
            }

            is NotificationFormEvent.ShowUpdateDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val notification = notificationDao.getNotificationById(event.id)
                        state = state.copy(
                            showUpdateDialog = true,
                            showUpdateDialogId = event.id,
                            createdAt = notification.createdAt,
                            notificationTitle = notification.title,
                            notificationDescription = notification.description,
                        )
                    }
                } else {
                    state = state.copy(showUpdateDialog = true)
                }
            }

            is NotificationFormEvent.DismissUpdateDialog -> {
                state = state.copy(
                    showUpdateDialog = false,
                    showUpdateDialogId = null,
                    createdAt = null,
                    notificationTitle = "",
                    notificationTitleError = null,
                    notificationDescription = "",
                    notificationDescriptionError = null,
                )
            }

            is NotificationFormEvent.NotificationTitleChanged -> {
                state = state.copy(notificationTitle = event.notificationTitle, notificationTitleError = null)
            }

            is NotificationFormEvent.NotificationDescriptionChanged -> {
                state = state.copy(notificationDescription = event.notificationDescription, notificationDescriptionError = null)
            }

            is NotificationFormEvent.NotificationQueryChanged -> {
                state = state.copy(notificationQuery = event.notificationQuery)
            }

            is NotificationFormEvent.SubmitUpdate -> {
                submitUpdate(event.universityId)
            }
        }
    }

    private fun submitUpdate(universityId: Int) {
        val titleResult = validateEmpty.execute(value = state.notificationTitle, fieldName = "Title")
        val descriptionResult = validateEmpty.execute(value = state.notificationDescription, fieldName = "Description")

        val hasError = listOf(
            titleResult,
            descriptionResult,
        ).any { !it.successful }
        
        if (hasError) {
            state = state.copy(
                notificationTitleError = titleResult.errorMessage,
                notificationDescriptionError = descriptionResult.errorMessage,
            )
            return
        }
        
        viewModelScope.launch { 
            if (state.showUpdateDialogId !== null) {
                notificationDao.updateNotification(
                    Notification(
                        id = state.showUpdateDialogId!!,
                        createdAt = state.createdAt?: Date(),
                        updatedAt = Date(),
                        universityId = universityId,
                        title = state.notificationTitle,
                        description = state.notificationDescription
                    )
                )
            } else {
                notificationDao.insertNotification(
                    Notification(
                        createdAt = Date(),
                        updatedAt = Date(),
                        universityId = universityId,
                        title = state.notificationTitle,
                        description = state.notificationDescription,
                    )
                )
            }
        }

        state = state.copy(
            showUpdateDialog = false,
            showUpdateDialogId = null,
            createdAt = null,
            notificationTitle = "",
            notificationTitleError = null,
            notificationDescription = "",
            notificationDescriptionError = null,
        )
    }
}