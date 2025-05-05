package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discussion")
data class Discussion (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val universityId: Int,
    val title: String,
    val description: String,
    val isActive: Boolean,
)