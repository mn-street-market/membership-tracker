package com.mnstreetmarket.membershiptracker.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value('${spring.h2.console.enabled}')
    boolean h2ConsoleEnabled

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers('/', '/join/**', '/h2-console/**').permitAll()
                .anyRequest().authenticated().and().formLogin()

        if (h2ConsoleEnabled) {
            http.csrf().disable()
            http.headers().frameOptions().disable()
        }
    }
}
