package com.mnstreetmarket.membershiptracker.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mnstreetmarket.membershiptracker.config.StripeConfig
import com.mnstreetmarket.membershiptracker.enums.ApplicationView
import com.stripe.model.checkout.Session
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.annotation.security.RolesAllowed

@Controller
@RequestMapping('/pay')
@RolesAllowed(['ROLE_USER'])
@Slf4j
class PaymentController {

    @Autowired
    StripeConfig stripeConfig
    
    @Autowired
    ObjectMapper objectMapper

    @GetMapping
    String get(@RequestParam(required = false, name = "session_id") String checkoutSessionId, Model model) {
        
        Session session = Session.retrieve(checkoutSessionId);
        
        if(session.paymentStatus == 'paid') {
            model.addAttribute("alert", "it worked! $session.customerEmail has paid!")
        } else {
            model.addAttribute('alert', 'failure!')
        }
        
        return ApplicationView.PAYMENT
    }
    
    @PostMapping
    String post(@RequestBody Session checkoutSession) {
        log.info("payment accepted $checkoutSession")
        return ApplicationView.PAYMENT
    }

}
