package com.mnstreetmarket.membershiptracker.dto

import com.mnstreetmarket.membershiptracker.trait.ErrorTrait

import javax.validation.constraints.NotBlank

class ApplicationRegisterDto implements ErrorTrait {

    @NotBlank(message = 'Password must not be blank')
    String password1
    String password2

}
