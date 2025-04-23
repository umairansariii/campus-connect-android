package com.umairansariii.campusconnect.data.local.dto

import com.umairansariii.campusconnect.data.local.enums.EventType
import java.util.Date

data class EventUniversity (
    val id: Int,
    val universityId: Int,
    val title: String,
    val description: String,
    val venue: String,
    val date: Date,
    val type: EventType,
    val isActive: Boolean,
    val universityName: String,
    val campusName: String,
)
