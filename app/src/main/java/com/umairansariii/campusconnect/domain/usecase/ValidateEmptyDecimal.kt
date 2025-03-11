package com.umairansariii.campusconnect.domain.usecase

class ValidateEmptyDecimal {
    fun execute(value: String, fieldName: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName is required",
            )
        }

        val decimalRegex = "-?\\d+(\\.\\d+)?".toRegex()

        if (!value.matches(decimalRegex)) {
            return ValidationResult(
                successful = false,
                errorMessage = "$fieldName must be decimal number",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}