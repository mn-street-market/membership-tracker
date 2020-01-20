package com.mnstreetmarket.membershiptracker.dto

import com.mnstreetmarket.membershiptracker.trait.ErrorTrait

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class ApplicationContactInfoDto implements ErrorTrait {

    @NotBlank(message = 'First Name is required')
    String firstName

    @NotBlank(message = 'Last Name is required')
    String lastName

    @NotBlank(message = 'Email is required')
    @Email(message = 'Must Be a well-formed email address')
    String email

    @NotBlank(message = 'Phone Number is required')
    String phoneNumber

    boolean student

}
