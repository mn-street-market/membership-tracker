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

import java.sql.Timestamp

@Service
class ApplicationService {

    @Autowired
    MemberRepository memberRepository

    boolean submit(ApplicationDto application) {

        if (memberRepository.findByEmail(application.contactInfo.email).isPresent()) {
            application.errorMessages << "Member with email $application.contactInfo.email already exists!"
        }

        if (application.register.password1 != application.register.password2) {
            application.errorMessages << 'Passwords do not match'
        }

        boolean isValid = application.errorMessages.isEmpty()
        if (application.isValid()) {
            memberRepository.save(toMemberEntity(application))
        }

        return isValid

    }

    private MemberEntity toMemberEntity(ApplicationDto application) {
        new MemberEntity(
                firstName: application.contactInfo.firstName,
                lastName: application.contactInfo.lastName,
                email: application.contactInfo.email,
                membershipFeeAmount: application.contactInfo.student ? 20.0 : 100.0,
                joinDate: new Timestamp(System.currentTimeMillis()),
                addresses: [
                        new MemberAddressEntity(
                                streetAddress: [application.address.streetAddress, application.address.apartmentNumber].join(' '),
                                city: application.address.city,
                                state: application.address.state,
                                zipCode: application.address.zipCode,
                        )
                ],
                phoneNumbers: [
                        new MemberPhoneEntity(
                                phoneNumber: application.contactInfo.phoneNumber,
                        )
                ],
                family: application.contactInfo.student ? [] : application.familyMembers.collect {
                    new MemberFamilyEntity(
                            name: it
                    )
                },
                password: encode(application.register.password1),
        )
    }

    static String encode(String unencoded) {
        "{bcrypt}${new BCryptPasswordEncoder().encode(unencoded)}"
    }
}
