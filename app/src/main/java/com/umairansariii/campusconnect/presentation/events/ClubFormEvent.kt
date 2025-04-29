package com.umairansariii.campusconnect.presentation.events

sealed interface ClubFormEvent {
    data class ShowDialog(val id: Int?): ClubFormEvent
    class DismissDialog: ClubFormEvent
    data class ClubTitleChanged(val clubTitle: String): ClubFormEvent
    data class ClubDescriptionChanged(val clubDescription: String): ClubFormEvent
    data class ClubStateChanged(val clubIsActive: Boolean): ClubFormEvent
    data class ClubQueryChanged(val clubQuery: String): ClubFormEvent
    data class Submit(val universityId: Int): ClubFormEvent
}