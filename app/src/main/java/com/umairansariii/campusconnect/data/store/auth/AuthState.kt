package com.umairansariii.campusconnect.data.store.auth

import com.umairansariii.campusconnect.data.local.enums.UserRole
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import kotlinx.serialization.Serializable

@Serializable
data class AuthState (
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val role: UserRole? = null,
    val status: UserStatus? = null,
    val isAuthenticated: Boolean = false,
)