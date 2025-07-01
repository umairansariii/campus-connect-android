package com.umairansariii.campusconnect.data.local.dto

data class ClubUniversity (
    val id: Int,
    val universityId: Int,
    val title: String,
    val description: String,
    val isActive: Boolean,
    val bannerUrl: String,
    val universityName: String,
    val campusName: String,
)
