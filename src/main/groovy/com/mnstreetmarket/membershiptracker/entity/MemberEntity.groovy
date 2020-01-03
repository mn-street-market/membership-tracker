package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = 'member')
@SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1, initialValue = 100)
class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    Integer memberId

    String firstName

    String lastName

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberAddressEntity> addresses = []

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberEmailEntity> emails = []

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberPhoneEntity> phoneNumbers = []

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberFamilyEntity> family = []

    boolean isActive

}
