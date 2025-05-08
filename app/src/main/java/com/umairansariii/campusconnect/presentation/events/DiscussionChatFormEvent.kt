package com.umairansariii.campusconnect.presentation.events

sealed interface DiscussionChatFormEvent {
    data class MessageChanged(val message: String): DiscussionChatFormEvent
    data class Submit(val discussionId: Int, val userId: Int): DiscussionChatFormEvent
}