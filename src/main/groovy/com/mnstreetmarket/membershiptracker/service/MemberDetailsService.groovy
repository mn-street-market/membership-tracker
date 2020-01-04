package com.mnstreetmarket.membershiptracker.service

import com.mnstreetmarket.membershiptracker.entity.MemberEntity
import com.mnstreetmarket.membershiptracker.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MemberDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        findByUsername(username).orElseThrow { new UsernameNotFoundException(username) }
    }

    Optional<User> findByUsername(String username) {
        User user = users.find { it.username == username }
        Optional.ofNullable(user)
    }

    List<User> getUsers() {
        memberRepository.findAll().collect { asUser(it) }
    }

    static User asUser(MemberEntity member) {
        List<GrantedAuthority> authorities = [
                new SimpleGrantedAuthority('ROLE_USER')
        ]

        if (member.email == 'c@test.com') {
            authorities << new SimpleGrantedAuthority('ROLE_ADMIN')
        }

        new User(member.email, member.password, authorities)
    }
}
