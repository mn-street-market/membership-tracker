package com.mnstreetmarket.membershiptracker.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.Email

@Entity
@Table(name = 'member_email',
        uniqueConstraints = [
                @UniqueConstraint(columnNames = 'emailAddress')
        ])
class MemberEmailEntity {

    @Id
    @Email(regexp = '.+@.+\\..+')
    String emailAddress

}
