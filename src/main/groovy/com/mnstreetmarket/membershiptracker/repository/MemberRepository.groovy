package com.mnstreetmarket.membershiptracker.repository

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
}
