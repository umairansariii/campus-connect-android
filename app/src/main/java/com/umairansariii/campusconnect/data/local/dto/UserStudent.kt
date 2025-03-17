package com.umairansariii.campusconnect.data.local.dto

import com.umairansariii.campusconnect.data.local.enums.UserStatus
import java.util.Date

data class UserStudent (
    val id: Int,
    val createdAt: Date,
    val firstName: String,
    val lastName: String,
    val email: String,
    val status: UserStatus,
    val rollNo: String,
)