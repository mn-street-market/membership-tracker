package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.dto.ApplicationDto
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import com.mnstreetmarket.membershiptracker.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/join")
class ApplicationController {

    @Autowired
    ApplicationService applicationService

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    String get(@RequestParam(required = false) Integer referralCode, Model model) {
        if (referralCode) {
            model.addAttribute('referral', memberRepository.findById(referralCode).orElseThrow {
                new IllegalArgumentException('Invalid Referral Code')
            })
        }
        model.addAttribute('application', new ApplicationDto())
        return 'application'
    }

    @PostMapping
    String post(@ModelAttribute ApplicationDto application, Model model) {

        boolean success = applicationService.submit(application)

        model.addAttribute('application', application)
        model.addAttribute('error', application.errorMessage) // will be null if success

        return success ? 'home' : 'application'
    }
}
