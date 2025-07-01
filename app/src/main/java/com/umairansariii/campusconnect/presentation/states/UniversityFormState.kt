package com.umairansariii.campusconnect.presentation.states

import android.net.Uri

data class UniversityFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val universityTitle: String = "",
    val universityTitleError: String? = null,
    val universityAvatarUrl: Uri? = null,
)