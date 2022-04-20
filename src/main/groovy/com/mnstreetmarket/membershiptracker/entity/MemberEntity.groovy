package com.mnstreetmarket.membershiptracker.entity

import groovy.transform.ToString

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import java.sql.Timestamp

@Entity
@Table(name = 'member')
@ToString
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
    Boolean isAdmin

    @Column(nullable = true)
    Timestamp joinDate

    @Column(nullable = true)
    Timestamp membershipPaidDate

    @Column(nullable = true)
    Double membershipFeeAmount

    @Column(nullable = true)
    Timestamp emailVerifiedDate

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = 'member_id')
    List<MemberAddressEntity> addresses = []

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = 'member_id')
    List<MemberPhoneEntity> phoneNumbers = []

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = 'member_id')
    List<MemberFamilyEntity> family = []

}
