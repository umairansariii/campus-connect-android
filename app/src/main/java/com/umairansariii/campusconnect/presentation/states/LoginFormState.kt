package com.umairansariii.campusconnect.presentation.states

data class LoginFormState (
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)