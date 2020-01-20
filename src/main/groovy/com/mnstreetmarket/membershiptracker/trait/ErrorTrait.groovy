package com.mnstreetmarket.membershiptracker.trait


import javax.validation.Validation
import javax.validation.Validator

trait ErrorTrait {

    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    void validate() {
        preValidate()
        errorMessages.addAll(validator.validate(this).collect { it.message })
        postValidate()
    }

    void preValidate() {

    }

    void postValidate() {

    }

    List<String> errorMessages = []

    String getErrorMessage() {
        errorMessages ? "Error: ${errorMessages.join(', ')}" : null
    }

    boolean isValid() {
        validate()
        return errorMessages.isEmpty()
    }
}
