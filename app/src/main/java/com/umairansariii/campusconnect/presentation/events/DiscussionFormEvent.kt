package com.umairansariii.campusconnect.presentation.events

sealed interface DiscussionFormEvent {
    data class ShowDialog(val id: Int?): DiscussionFormEvent
    class DismissDialog: DiscussionFormEvent
    data class DiscussionTitleChanged(val discussionTitle: String): DiscussionFormEvent
    data class DiscussionDescriptionChanged(val discussionDescription: String): DiscussionFormEvent
    data class DiscussionStateChanged(val discussionIsActive: Boolean): DiscussionFormEvent
    data class DiscussionQueryChanged(val discussionQuery: String): DiscussionFormEvent
    data class Submit(val universityId: Int): DiscussionFormEvent
}