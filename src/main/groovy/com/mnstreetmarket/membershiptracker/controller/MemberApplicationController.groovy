package com.mnstreetmarket.membershiptracker.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/application")
class MemberApplicationController {

    @GetMapping
    String get() {
        return 'application'
    }

}
