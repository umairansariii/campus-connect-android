package com.umairansariii.campusconnect.domain.usecase

class ValidateEmptyAlpha {
    fun execute(value: String, fieldName: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is required",
            )
        }

        val isAlphaOrSpace = value.all { it.isLetter() || it.isWhitespace() }

        if (!isAlphaOrSpace) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName must contain only letters",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}