package com.umairansariii.campusconnect.domain.usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)