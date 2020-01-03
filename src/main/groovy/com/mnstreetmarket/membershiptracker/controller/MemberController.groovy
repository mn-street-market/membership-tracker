package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/members')
class MemberController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    List<MemberEntity> get(){
        memberRepository.findAll()
    }

}
