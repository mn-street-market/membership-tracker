package com.mnstreetmarket.membershiptracker.dto

import com.mnstreetmarket.membershiptracker.trait.ErrorTrait

import javax.validation.Valid

class ApplicationDto implements ErrorTrait {

    @Valid
    ApplicationContactInfoDto contactInfo

    @Valid
    ApplicationAddressDto address

    @Valid
    ApplicationFamilyMembersDto familyMembers

    @Valid
    ApplicationRegisterDto register

}
