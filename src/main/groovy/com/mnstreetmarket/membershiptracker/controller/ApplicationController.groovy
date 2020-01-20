package com.mnstreetmarket.membershiptracker.controller

import com.mnstreetmarket.membershiptracker.dto.ApplicationAddressDto
import com.mnstreetmarket.membershiptracker.dto.ApplicationContactInfoDto
import com.mnstreetmarket.membershiptracker.dto.ApplicationDto
import com.mnstreetmarket.membershiptracker.dto.ApplicationFamilyMembersDto
import com.mnstreetmarket.membershiptracker.dto.ApplicationRegisterDto
import com.mnstreetmarket.membershiptracker.enums.ApplicationView
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import com.mnstreetmarket.membershiptracker.service.ApplicationService
import com.mnstreetmarket.membershiptracker.trait.ErrorTrait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@RequestMapping('/join')
@SessionAttributes('application')
class ApplicationController {

    @Autowired
    ApplicationService applicationService

    @Autowired
    MemberRepository memberRepository

    @ModelAttribute('application')
    ApplicationDto application() {
        new ApplicationDto(
                contactInfo: new ApplicationContactInfoDto(),
                familyMembers: new ApplicationFamilyMembersDto(),
                register: new ApplicationRegisterDto(),
                address: new ApplicationAddressDto(),
        )
    }

    @GetMapping
    String get(@RequestParam(required = false) Integer referralCode, Model model) {
        if (referralCode) {
            model.addAttribute('referral', memberRepository.findById(referralCode).orElseThrow {
                new IllegalArgumentException('Invalid Referral Code')
            })
        }
        bindAttributes(model)
        return ApplicationView.CONTACT
    }

    void bindAttributes(Model model, ErrorTrait errorTrait = null) {
        model.addAttribute('contactInfo', getApplication(model).contactInfo)
        model.addAttribute('address', getApplication(model).address)
        model.addAttribute('familyMembers', getApplication(model).familyMembers)
        model.addAttribute('register', getApplication(model).register)
        if (errorTrait) {
            model.addAttribute('error', errorTrait.valid ? null : errorTrait.errorMessage)
        }
    }

    @PostMapping('/contact')
    String post(@ModelAttribute ApplicationContactInfoDto contactInfo, Model model) {

        getApplication(model).contactInfo = contactInfo
        bindAttributes(model, contactInfo)

        return contactInfo.valid ? ApplicationView.ADDRESS : ApplicationView.CONTACT
    }

    private ApplicationDto getApplication(Model model) {
        model.getAttribute('application') as ApplicationDto
    }

    @PostMapping('/address')
    String post(@ModelAttribute ApplicationAddressDto address, Model model) {
        getApplication(model).address = address
        bindAttributes(model, address)

        return !address.valid ? ApplicationView.ADDRESS :
                getApplication(model).contactInfo.isStudent() ? ApplicationView.REGISTER :
                        ApplicationView.FAMILY_MEMBERS
    }

    @PostMapping('/familyMembers')
    String post(@ModelAttribute ApplicationFamilyMembersDto familyMembers, Model model) {

        getApplication(model).familyMembers = familyMembers
        bindAttributes(model, familyMembers)

        return familyMembers.valid ? ApplicationView.REGISTER : ApplicationView.FAMILY_MEMBERS
    }

    @PostMapping('/register')
    String post(@ModelAttribute ApplicationRegisterDto register, Model model) {
        getApplication(model).register = register
        bindAttributes(model, register)

        applicationService.submit(getApplication(model))

        if (register.valid) {
            model.addAttribute('alert', 'You have successfully completed your application!')
        }

        return register.valid ? 'home' : ApplicationView.REGISTER
    }
}
