package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "club_chat")
data class ClubChat (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clubId: Int,
    val senderId: Int,
    val message: String,
    val timestamp: Date,
)
