package com.umairansariii.campusconnect.domain.usecase

class ValidateEmptyAlpha {
    fun execute(value: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "is required",
            )
        }

        val isAlpha = value.all { it.isLetter() }

        if (!isAlpha) {
            return ValidationResult(
                successful = false,
                errorMessage = "must contain only letters",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}