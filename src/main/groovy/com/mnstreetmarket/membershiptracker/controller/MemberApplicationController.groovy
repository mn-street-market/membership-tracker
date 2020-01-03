package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/application")
class MemberApplicationController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    String get() {
        return 'application'
    }

    @PostMapping
    String post(@ModelAttribute MemberEntity member, Model model) {
        memberRepository.save(member)
        return getMembers(model)
    }

    @GetMapping('/members')
    String getMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll())
        return 'members'
    }

}
