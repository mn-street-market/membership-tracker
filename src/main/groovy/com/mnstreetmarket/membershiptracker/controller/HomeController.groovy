package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import java.security.Principal

@Controller
@RequestMapping("/")
class HomeController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    String get(Principal principal, Model model) {
        MemberEntity member = memberRepository.findByEmail(principal?.getName()).orElse(null)

        if (member) {
            model.addAttribute('member', member)
        }

        return 'home'
    }

    @GetMapping('/username')
    @ResponseBody
    String currentUserName(Principal principal) {
        return principal.getName()
    }

}
