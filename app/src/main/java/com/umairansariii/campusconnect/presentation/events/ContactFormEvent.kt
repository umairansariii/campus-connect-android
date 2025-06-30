package com.umairansariii.campusconnect.presentation.events

import com.umairansariii.campusconnect.data.local.enums.ContactType

sealed interface ContactFormEvent {
    data class ShowDialog(val id: Int?): ContactFormEvent
    class DismissDialog: ContactFormEvent
    data class ContactNameChanged(val contactName: String): ContactFormEvent
    data class ContactPhoneChanged(val contactPhone: String): ContactFormEvent
    data class ContactEmailChanged(val contactEmail: String): ContactFormEvent
    data class ContactTypeChanged(val contactType: ContactType): ContactFormEvent
    data class ContactQueryChanged(val contactQuery: String): ContactFormEvent
    data class Submit(val universityId: Int): ContactFormEvent
}