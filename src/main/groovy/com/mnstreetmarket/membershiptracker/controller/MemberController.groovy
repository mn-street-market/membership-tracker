package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.dto.ApplicationDto
import com.mnstreetmarket.membershiptracker.entity.MemberEmailEntity
import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/members")
class MemberController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping("/application")
    String get() {
        return 'application'
    }

    @PostMapping
    String post(
            @ModelAttribute ApplicationDto applicationDto,
            Model model) {
        memberRepository.save(new MemberEntity(
                firstName: applicationDto.firstName,
                lastName: applicationDto.lastName,
                emails: [new MemberEmailEntity(emailAddress: applicationDto.email)]
        ))
        return getMembers(model)
    }

    @GetMapping
    String getMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll())
        return 'members'
    }

    @GetMapping("/{memberId}")
    String getMember(@PathVariable int memberId, Model model) {
        model.addAttribute("member", memberRepository.findById(memberId).orElseThrow({
            new IllegalArgumentException("Member $memberId does not exist")
        }))
        return 'view-member'
    }

}
