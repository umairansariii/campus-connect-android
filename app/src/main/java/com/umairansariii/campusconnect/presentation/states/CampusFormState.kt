package com.umairansariii.campusconnect.presentation.states

data class CampusFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val campusTitle: String = "",
    val campusTitleError: String? = null,
    val campusCode: String = "",
    val campusCodeError: String? = null,
    val campusLatitude: String = "",
    val campusLatitudeError: String? = null,
    val campusLongitude: String = "",
    val campusLongitudeError: String? = null,
)