package com.umairansariii.campusconnect.presentation.events

import android.net.Uri

sealed interface UniversityFormEvent {
    data class ShowDialog(val id: Int?): UniversityFormEvent
    class DismissDialog: UniversityFormEvent
    data class UniversityTitleChanged(val universityTitle: String): UniversityFormEvent
    data class UniversityAvatarChanged(val universityAvatar: Uri?): UniversityFormEvent
    data class Submit(val adminId: Int?): UniversityFormEvent
}