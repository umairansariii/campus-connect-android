package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department")
data class Department (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val universityId: Int,
    val title: String,
)