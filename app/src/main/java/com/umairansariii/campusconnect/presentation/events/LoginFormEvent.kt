package com.umairansariii.campusconnect.presentation.events

sealed interface LoginFormEvent {
    data class EmailChanged(val email: String): LoginFormEvent
    data class PasswordChanged(val password: String): LoginFormEvent
    class Submit: LoginFormEvent
}