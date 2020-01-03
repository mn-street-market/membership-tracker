package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'member_family')
class MemberFamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int memberFamilyId

    String firstName

    String lastName

}
