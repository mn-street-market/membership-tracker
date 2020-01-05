package com.mnstreetmarket.membershiptracker.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class ApplicationDto {

    @NotBlank(message = 'First Name must not be blank')
    String firstName

    @NotBlank(message = 'Last Name must not be blank')
    String lastName

    @Email(message = 'Must Be a well-formed email address')
    String email

    @NotBlank(message = 'Phone Number must not be blank')
    String phoneNumber

    @NotBlank(message = 'Password must not be blank')
    String password1
    String password2

    @NotBlank(message = 'Street Address must not be blank')
    String streetAddress
    String apartmentNumber

    @NotBlank(message = 'City must not be blank')
    String city

    @NotBlank(message = 'State must not be blank')
    String state

    @NotBlank(message = 'Zip Code must not be blank')
    String zipCode

    boolean student

    List<String> familyMembers = []

    List<String> errorMessages = []

    String getErrorMessage() {
        errorMessages ? "Error: ${errorMessages.join(', ')}" : null
    }
}
