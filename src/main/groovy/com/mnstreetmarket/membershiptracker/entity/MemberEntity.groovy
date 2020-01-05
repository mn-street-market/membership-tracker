package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import java.sql.Date

@Entity
@Table(name = 'member')
class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer memberId

    String firstName
    String lastName
    String email
    String password
    @Column(nullable = true)
    Boolean isActive
    @Column(nullable = true)
    Boolean isStudentMember
    @Column(nullable = true)
    Boolean isEmailConfirmed
    @Column(nullable = true)
    Boolean isMembershipPaid
    @Column(nullable = true)
    Date joinDate

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberAddressEntity> addresses = []

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberPhoneEntity> phoneNumbers = []

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberFamilyEntity> family = []

}
