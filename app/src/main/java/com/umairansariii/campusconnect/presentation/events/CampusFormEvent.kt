package com.umairansariii.campusconnect.presentation.events

sealed interface CampusFormEvent {
    data class ShowDialog(val id: Int?): CampusFormEvent
    class DismissDialog: CampusFormEvent
    data class CampusTitleChanged(val campusTitle: String): CampusFormEvent
    data class CampusCodeChanged(val campusCode: String): CampusFormEvent
    data class CampusLatitudeChanged(val campusLatitude: String): CampusFormEvent
    data class CampusLongitudeChanged(val campusLongitude: String): CampusFormEvent
    data class CampusQueryChanged(val campusQuery: String): CampusFormEvent
    data class Submit(val universityId: Int): CampusFormEvent
}