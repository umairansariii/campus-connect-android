package com.umairansariii.campusconnect.domain.usecase

class ValidatePassword {
    fun execute(value: String, fieldName: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is required",
            )
        }

        if (value.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName must be at least 8 characters"
            )
        }

        val passwordPattern = Regex(pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).+\$")

        if (!value.matches(passwordPattern)) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is not strong enough"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}