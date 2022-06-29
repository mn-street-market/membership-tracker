package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.config.StripeConfig
import com.mnstreetmarket.membershiptracker.enums.ApplicationView
import com.stripe.model.Price
import com.stripe.model.checkout.Session
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.security.RolesAllowed

@Controller
@RequestMapping('/pay')
@RolesAllowed(['ROLE_USER'])
@Slf4j
class PaymentController {

    @Autowired
    StripeConfig stripeConfig

    @GetMapping
    String get(Model model) {
        Price price = Price.retrieve(stripeConfig.dotenv.get("PRICE"))
        model.addAttribute("price", price)
        return ApplicationView.PAYMENT
    }
    
    @PostMapping
    String post(@RequestBody Session checkoutSession) {
        log.info("payment accepted $checkoutSession")
        return ApplicationView.PAYMENT
    }

}
