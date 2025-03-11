package com.umairansariii.campusconnect.domain.usecase

class ValidateEmpty {
    fun execute(value: String, fieldName: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is required",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}