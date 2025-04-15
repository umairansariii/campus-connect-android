package com.umairansariii.campusconnect.presentation.events

sealed interface NotificationFormEvent {
    data class ShowViewDialog(val id: Int): NotificationFormEvent
    class DismissViewDialog: NotificationFormEvent
    data class ShowUpdateDialog(val id: Int?): NotificationFormEvent
    class DismissUpdateDialog: NotificationFormEvent
    data class NotificationTitleChanged(val notificationTitle: String): NotificationFormEvent
    data class NotificationDescriptionChanged(val notificationDescription: String): NotificationFormEvent
    data class NotificationQueryChanged(val notificationQuery: String): NotificationFormEvent
    data class SubmitUpdate(val universityId: Int): NotificationFormEvent
}