package com.umairansariii.campusconnect.presentation.states

import java.util.Date

data class NotificationFormState (
    val showViewDialog: Boolean = false,
    val showViewDialogId: Int? = null,
    val showUpdateDialog: Boolean = false,
    val showUpdateDialogId: Int? = null,
    // Notification Data
    val createdAt: Date? = null,
    val notificationTitle: String = "",
    val notificationTitleError: String? = null,
    val notificationDescription: String = "",
    val notificationDescriptionError: String? = null,
    val notificationQuery: String = "",
)
