package com.mnstreetmarket.membershiptracker.enums

enum ApplicationView {
    CONTACT('application-contact-info'),
    ADDRESS('application-address'),
    STUDENT('application-student'),
    FAMILY_MEMBERS('application-family-members'),
    REGISTER('application-register'),
    PAYMENT('pay'),

    final String viewName

    ApplicationView(String viewName) {
        this.viewName = viewName
    }

    String toString() {
        return viewName
    }
}
