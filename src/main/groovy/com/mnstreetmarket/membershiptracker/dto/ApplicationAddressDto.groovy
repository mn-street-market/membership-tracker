package com.mnstreetmarket.membershiptracker.dto

import com.mnstreetmarket.membershiptracker.trait.ErrorTrait

import javax.validation.constraints.NotBlank

class ApplicationAddressDto implements ErrorTrait {

    @NotBlank(message = 'Street Address must not be blank')
    String streetAddress
    String apartmentNumber

    @NotBlank(message = 'City must not be blank')
    String city

    @NotBlank(message = 'State must not be blank')
    String state

    @NotBlank(message = 'Zip Code must not be blank')
    String zipCode

}
