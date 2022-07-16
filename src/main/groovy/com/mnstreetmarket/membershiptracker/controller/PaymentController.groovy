package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.enums.ApplicationView
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import com.stripe.model.checkout.Session
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.annotation.security.RolesAllowed
import java.sql.Timestamp
import java.time.Instant

@Controller
@RequestMapping('/pay')
@RolesAllowed(['ROLE_USER'])
@Slf4j
class PaymentController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    String get(@RequestParam(required = false, name = "session_id") String checkoutSessionId, Model model) {

        Session session = Session.retrieve(checkoutSessionId);

        if (session.paymentStatus == 'paid') {
            MemberEntity member = memberRepository.findByEmail(session.customerEmail)
                    .orElseThrow { new IllegalArgumentException("Unable to find customer with email '${session.customerEmail}'") }
            member.setMembershipPaidDate(new Timestamp(Instant.now().toEpochMilli()))
            memberRepository.save(member)
            model.addAttribute("alert", "it worked! $session.customerEmail has paid!")
        } else {
            model.addAttribute('alert', 'payment not received')
        }

        return ApplicationView.PAYMENT
    }

}
