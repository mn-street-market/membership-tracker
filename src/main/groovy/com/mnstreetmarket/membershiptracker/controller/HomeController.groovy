package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.config.StripeConfig
import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import com.stripe.model.checkout.Session
import com.stripe.param.checkout.SessionCreateParams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.util.UriComponentsBuilder

import javax.annotation.security.RolesAllowed
import java.security.Principal

@Controller
@RequestMapping("/")
class HomeController {
    
    @Autowired
    StripeConfig stripeConfig

    @Autowired
    MemberRepository memberRepository

    @Value('${baseurl}')
    String baseUrl;

    @GetMapping
    String get(Principal principal, Model model) {
        bindMember(principal, model)
        return 'home'
    }

    @GetMapping('my-account')
    @RolesAllowed(['ROLE_USER'])
    String getMyAccount(Principal principal, Model model) {
        bindMember(principal, model)
        return 'view-member'
    }

    @GetMapping('my-account/edit')
    @RolesAllowed(['ROLE_USER'])
    String editMyAccount(Principal principal, Model model) {
        bindMember(principal, model)
        return 'edit-member'
    }

    @PostMapping('my-account/update')
    @RolesAllowed(['ROLE_USER'])
    String updateMyAccount(Principal principal, Model model, @ModelAttribute MemberEntity updated) {
        bindMember(principal, model)

        MemberEntity current = model.getAttribute('member')
        if (updated.memberId != current.memberId) {
            throw new IllegalArgumentException()
        }

        updated.password = current.password
        memberRepository.save(updated)

        return 'view-member'
    }

    @GetMapping('refer-friend')
    @RolesAllowed(['ROLE_USER'])
    String getReferFriend(Principal principal, Model model, UriComponentsBuilder uriComponentsBuilder) {
        MemberEntity member = memberRepository.findByEmail(principal?.getName()).orElse(null)

        String link = uriComponentsBuilder
                .replacePath("/join")
                .replaceQuery("referralCode=$member.memberId")
                .build().toUri().toString()

        model.addAttribute('referralLink', link)

        return 'refer-friend'
    }

    private void bindMember(Principal principal, Model model) {
        MemberEntity member = memberRepository.findByEmail(principal?.getName()).orElse(null)
        model.addAttribute('member', member)
        if(member && !member.membershipPaidDate) {
            model.addAttribute('paymentUrl', getPaymentUrl(member))
        }
    }
    
    private String getPaymentUrl(MemberEntity member) {
        SessionCreateParams createParams = new SessionCreateParams.Builder()
                .setSuccessUrl("$baseUrl/pay?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl("$baseUrl/canceled")
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCustomerEmail(member.email)
                .addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1)
                        .setPrice(stripeConfig.membershipPrice)
                        .build())
                .build()

        Session session = Session.create(createParams)
        return session.url
    }

}
