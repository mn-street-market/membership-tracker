package com.mnstreetmarket.membershiptracker.enums

enum ApplicationView {
    CONTACT('application-contact-info'),
    ADDRESS('application-address'),
    STUDENT('application-student'),
    FAMILY_MEMBERS('application-family-members'),
    REGISTER('application-register'),
    PAYMENT('pay'),
    MEMBERS('members'),
    VIEW_MEMBER('view-member'),
    EDIT_MEMBER('edit-member'),

    final String viewName

    ApplicationView(String viewName) {
        this.viewName = viewName
    }

    String toString() {
        return viewName
    }
}
