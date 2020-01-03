package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'member')
class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id

    String firstName

    String lastName

}
