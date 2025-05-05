package com.umairansariii.campusconnect.presentation.events

sealed interface DiscussionFormEvent {
    data class ShowDialog(val id: Int?): DiscussionFormEvent
    class DismissDialog: DiscussionFormEvent
    data class DiscussionTitleChanged(val clubTitle: String): DiscussionFormEvent
    data class DiscussionDescriptionChanged(val clubDescription: String): DiscussionFormEvent
    data class DiscussionStateChanged(val clubIsActive: Boolean): DiscussionFormEvent
    data class DiscussionQueryChanged(val clubQuery: String): DiscussionFormEvent
    data class Submit(val universityId: Int): DiscussionFormEvent
}