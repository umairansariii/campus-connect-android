package com.umairansariii.campusconnect.data.local.dto

import com.umairansariii.campusconnect.data.local.enums.UserRole
import java.util.Date

data class ClubChatUser (
    val id: Int,
    val clubId: Int,
    val senderId: Int,
    val message: String,
    val timestamp: Date,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
)