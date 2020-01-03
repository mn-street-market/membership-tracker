package com.mnstreetmarket.membershiptracker.entity


import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = 'member')
class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer memberId

    String firstName

    String lastName

    @OneToMany
    @JoinColumn(name = 'member_id')
    List<MemberAddressEntity> addresses = []

    @OneToMany(cascade = CascadeType.ALL)
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
