package com.umairansariii.campusconnect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umairansariii.campusconnect.data.local.enums.ContactType

@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val universityId: Int,
    val name: String,
    val phone: String,
    val email: String,
    val type: ContactType,
)