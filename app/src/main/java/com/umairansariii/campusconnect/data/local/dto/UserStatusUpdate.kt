package com.umairansariii.campusconnect.data.local.dto

import com.umairansariii.campusconnect.data.local.enums.UserStatus

data class UserStatusUpdate (
    val id: Int,
    val status: UserStatus,
)