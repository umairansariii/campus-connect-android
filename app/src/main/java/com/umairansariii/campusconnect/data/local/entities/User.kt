package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umairansariii.campusconnect.data.local.enums.UserRole
import com.umairansariii.campusconnect.data.local.enums.UserStatus

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: UserRole,
    val status: UserStatus,
)