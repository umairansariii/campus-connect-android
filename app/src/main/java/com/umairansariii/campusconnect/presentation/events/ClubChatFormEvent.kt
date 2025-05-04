package com.umairansariii.campusconnect.presentation.events

sealed interface ClubChatFormEvent {
    data class MessageChanged(val message: String): ClubChatFormEvent
    data class Submit(val clubId: Int, val userId: Int): ClubChatFormEvent
}