package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    String get() {
        return 'home'
    }

}
