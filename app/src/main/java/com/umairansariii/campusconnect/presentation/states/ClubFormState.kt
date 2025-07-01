package com.umairansariii.campusconnect.presentation.states

import android.net.Uri

data class ClubFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val clubTitle: String = "",
    val clubTitleError: String? = null,
    val clubDescription: String = "",
    val clubDescriptionError: String? = null,
    val clubIsActive: Boolean = true,
    val clubBannerUrl: Uri? = null,
    val clubQuery: String = "",
)
