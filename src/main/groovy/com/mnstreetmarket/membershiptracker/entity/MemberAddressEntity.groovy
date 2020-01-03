package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'member_address')
class MemberAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int memberAddressId

    String streetAddress

    String city

    String state

    String zipCode

}
