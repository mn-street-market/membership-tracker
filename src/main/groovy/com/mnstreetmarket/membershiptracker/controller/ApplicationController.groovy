package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.dto.ApplicationDto
import com.mnstreetmarket.membershiptracker.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/join")
class ApplicationController {

    @Autowired
    ApplicationService applicationService

    @GetMapping
    String get() {
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
