package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "campus")
data class Campus (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val universityId: Int,
    val title: String,
    val campusCode: String,
    val latitude: String,
    val longitude: String,
)