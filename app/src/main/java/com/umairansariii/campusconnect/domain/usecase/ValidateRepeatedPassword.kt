package com.umairansariii.campusconnect.domain.usecase

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password did not matched",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}