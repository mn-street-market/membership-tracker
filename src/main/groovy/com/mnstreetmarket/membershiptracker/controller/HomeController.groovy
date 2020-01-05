package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.util.UriComponentsBuilder

import javax.annotation.security.RolesAllowed
import java.security.Principal

@Controller
@RequestMapping("/")
class HomeController {

    @Autowired
    MemberRepository memberRepository

    @GetMapping
    String get(Principal principal, Model model) {
        MemberEntity member = memberRepository.findByEmail(principal?.getName()).orElse(null)

        if (member) {
            model.addAttribute('member', member)
        }

        return 'home'
    }

    @GetMapping('my-account')
    @RolesAllowed(['ROLE_USER'])
    String getMyAccount(Principal principal, Model model) {
        MemberEntity member = memberRepository.findByEmail(principal?.getName()).orElse(null)

        if (member) {
            model.addAttribute('member', member)
        }

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

}
