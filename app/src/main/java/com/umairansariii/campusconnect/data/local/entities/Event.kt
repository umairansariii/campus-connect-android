package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umairansariii.campusconnect.data.local.enums.EventType
import java.util.Date

@Entity(tableName = "event")
data class Event (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val universityId: Int,
    val title: String,
    val description: String,
    val venue: String,
    val date: Date,
    val type: EventType,
    val isActive: Boolean,
)