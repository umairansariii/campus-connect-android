package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "university")
data class University (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val adminId: Int,
    val title: String,
    val avatarUrl: String,
)