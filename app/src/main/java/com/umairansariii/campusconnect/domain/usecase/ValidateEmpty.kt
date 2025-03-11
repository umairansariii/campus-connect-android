package com.umairansariii.campusconnect.domain.usecase

class ValidateEmpty {
    fun execute(value: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "is required",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}