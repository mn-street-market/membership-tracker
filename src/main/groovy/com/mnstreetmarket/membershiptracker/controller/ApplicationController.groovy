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
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/join")
class ApplicationController {

    @Autowired
    ApplicationService applicationService

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    ModelAndView get(@RequestParam(required = false) Integer referralCode, Model model) {
        if (referralCode) {
            model.addAttribute('referral', memberRepository.findById(referralCode).orElseThrow {
                new IllegalArgumentException('Invalid Referral Code')
            })
        }
        if (!model.getAttribute('application')) {
            model.addAttribute('application', new ApplicationDto())
        }
        return new ModelAndView('application-contact-info', model.asMap())
    }

    @PostMapping
    ModelAndView post(@ModelAttribute('application') ApplicationDto application, Model model) {


        return new ModelAndView(application.currentView, model.asMap())
    }
}
