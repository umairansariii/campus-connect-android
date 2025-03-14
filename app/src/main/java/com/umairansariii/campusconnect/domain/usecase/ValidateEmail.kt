package com.umairansariii.campusconnect.domain.usecase

import android.util.Patterns

class ValidateEmail {
    fun execute(value: String, fieldName: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is required",
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is not valid address",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}