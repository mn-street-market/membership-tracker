package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'member_phone')
class MemberPhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int memberPhoneId

    String phoneNumber

}
