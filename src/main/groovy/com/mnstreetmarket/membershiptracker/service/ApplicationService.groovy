package com.mnstreetmarket.membershiptracker.service

import com.mnstreetmarket.membershiptracker.dto.ApplicationDto
import com.mnstreetmarket.membershiptracker.entity.MemberAddressEntity
import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.entity.MemberFamilyEntity
import com.mnstreetmarket.membershiptracker.entity.MemberPhoneEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import javax.validation.Valid
import javax.validation.Validation
import javax.validation.Validator
import java.sql.Timestamp

@Service
class ApplicationService {

    @Autowired
    MemberRepository memberRepository

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    boolean submit(@Valid ApplicationDto application) {

        validate(application)

        boolean isValid = application.errorMessages.isEmpty()
        if (isValid)
            memberRepository.save(new MemberEntity(
                    firstName: application.firstName,
                    lastName: application.lastName,
                    email: application.email,
                    membershipFeeAmount: application.student ? 20.0 : 100.0,
                    joinDate: new Timestamp(System.currentTimeMillis()),
                    addresses: [
                            new MemberAddressEntity(
                                    streetAddress: [application.streetAddress, application.apartmentNumber].join(' '),
                                    city: application.city,
                                    state: application.state,
                                    zipCode: application.zipCode,
                            )
                    ],
                    phoneNumbers: [
                            new MemberPhoneEntity(
                                    phoneNumber: application.phoneNumber,
                            )
                    ],
                    family: application.student ? [] : application.familyMembers.collect {
                        new MemberFamilyEntity(
                                name: it
                        )
                    },
                    password: encode(application.password1),
            ))

        return isValid

    }

    private void validate(ApplicationDto application) {
        application.errorMessages.addAll(validator.validate(application).collect { it.message })

        if (memberRepository.findByEmail(application.email).isPresent()) {
            application.errorMessages << "Member with email $application.email already exists!"
        }

        if (application.password1 != application.password2) {
            application.errorMessages << 'Passwords do not match'
        }
    }

    static String encode(String unencoded) {
        "{bcrypt}${new BCryptPasswordEncoder().encode(unencoded)}"
    }
}
