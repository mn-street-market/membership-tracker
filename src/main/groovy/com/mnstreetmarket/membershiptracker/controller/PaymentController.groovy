package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.enums.ApplicationView
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

import javax.annotation.security.RolesAllowed

@Controller
@RequestMapping('/pay')
@RolesAllowed(['ROLE_USER'])
class PaymentController {
    
    @GetMapping
    String get() {
        return ApplicationView.PAYMENT
    }
    
}
