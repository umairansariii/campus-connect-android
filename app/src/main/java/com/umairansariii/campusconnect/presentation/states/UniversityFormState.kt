package com.umairansariii.campusconnect.presentation.states

data class UniversityFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val universityTitle: String = "",
    val universityTitleError: String? = null,
)