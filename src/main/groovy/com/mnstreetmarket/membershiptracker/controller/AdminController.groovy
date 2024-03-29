package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.enums.ApplicationView
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.security.RolesAllowed

@Controller
@RequestMapping("/admin")
@RolesAllowed(['ROLE_ADMIN'])
class AdminController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping('/members')
    String getMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll())
        return ApplicationView.MEMBERS
    }

    @GetMapping('/members/{memberId}')
    String getMember(@PathVariable int memberId, Model model) {
        bindMember(model, memberId)
        return ApplicationView.VIEW_MEMBER
    }

    @GetMapping('/members/{memberId}/edit')
    String editMember(@PathVariable int memberId, Model model) {
        bindMember(model, memberId)
        return ApplicationView.EDIT_MEMBER
    }

    private Model bindMember(Model model, int memberId) {
        model.addAttribute("member", memberRepository.findById(memberId).orElseThrow({
            new IllegalArgumentException("Member $memberId does not exist")
        }))
    }
}
