package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "academic")
data class Academic (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val enrollmentId: Int,
    val semester: Int,
    val cgpa: Double,
)