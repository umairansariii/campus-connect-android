package com.umairansariii.campusconnect.presentation.states

import com.umairansariii.campusconnect.data.local.enums.ContactType

data class ContactFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val contactName: String = "",
    val contactNameError: String? = null,
    val contactPhone: String = "",
    val contactPhoneError: String? = null,
    val contactEmail: String = "",
    val contactEmailError: String? = null,
    val contactType: ContactType? = null,
    val contactTypeError: String? = null,
    val contactQuery: String = "",
)
