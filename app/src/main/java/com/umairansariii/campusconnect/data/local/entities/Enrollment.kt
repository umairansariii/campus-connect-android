package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umairansariii.campusconnect.data.local.enums.UserGender
import java.util.Date

@Entity(tableName = "enrollment")
data class Enrollment (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val universityId: Int,
    val campusId: Int,
    val departmentId: Int,
    val studentId: Int,
    val rollNo: String,
    val dob: Date,
    val gender: UserGender,
)