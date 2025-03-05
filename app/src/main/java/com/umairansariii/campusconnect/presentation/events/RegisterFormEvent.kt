package com.umairansariii.campusconnect.presentation.events

sealed interface RegisterFormEvent {
    data class FirstNameChanged(val firstName: String): RegisterFormEvent
    data class LastNameChanged(val lastName: String): RegisterFormEvent
    data class EmailChanged(val email: String): RegisterFormEvent
    data class PasswordChanged(val password: String): RegisterFormEvent
    data class RepeatedPasswordChanged(val repeatedPassword: String): RegisterFormEvent

    object Submit: RegisterFormEvent
}