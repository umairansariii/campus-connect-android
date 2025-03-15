package com.umairansariii.campusconnect.domain.usecase

class ValidateNull {
    fun <T> execute(value: T?, fieldName: String): ValidationResult {
        if (value == null) {
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