package com.ldap.example.ldapdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*  auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://10.75.49.60:389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");*/
        auth
                .ldapAuthentication()
               // .userDetailsContextMapper(inetOrgPersonContextMapper())
                .userSearchFilter("(uid={0})")
                .userSearchBase("dc=example,dc=org")
                .groupSearchBase("ou=people,dc=example,dc=org")
                .groupSearchFilter("cn={0}")
                .contextSource()
                .url("ldap://ip")
                .port(389)
                .managerDn("cn=admin,dc=example,dc=org")
                .managerPassword("admin");
    }
}