package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "discussion_chat")
data class DiscussionChat (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val discussionId: Int,
    val senderId: Int,
    val message: String,
    val timestamp: Date,
)