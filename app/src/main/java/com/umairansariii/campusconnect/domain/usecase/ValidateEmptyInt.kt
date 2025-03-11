package com.umairansariii.campusconnect.domain.usecase

class ValidateEmptyInt {
    fun execute(value: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "is required",
            )
        }

        val isInteger = value.all { it.isDigit() }

        if (!isInteger) {
            return ValidationResult(
                successful = false,
                errorMessage = "must contain only digits",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}