package com.umairansariii.campusconnect.domain.usecase

class ValidateEmptyInteger {
    fun execute(value: String, fieldName: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is required",
            )
        }

        val isInteger = value.all { it.isDigit() }

        if (!isInteger) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName must be integer",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}