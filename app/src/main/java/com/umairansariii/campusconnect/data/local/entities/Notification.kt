package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notification")
data class Notification (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val createdAt: Date,
    val updatedAt: Date,
    val universityId: Int,
    val title: String,
    val description: String,
)
