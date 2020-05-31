package ru.egprojects.sw1_springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ru.egprojects.sw1_springboot.model.Pages.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier(value = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.formLogin()
                .loginPage(SIGN_IN)
                .defaultSuccessUrl(HOME)
                .failureUrl(SIGN_IN + "?error")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll();

        http.authorizeRequests()
                .antMatchers(SIGN_UP).permitAll()
                .antMatchers(EMAIL_CONFIRMATION).permitAll()
                .antMatchers(HOME).permitAll()
                .antMatchers(USERS).permitAll()
                .antMatchers(PROFILE).permitAll()
                .antMatchers(EDIT_PROFILE).authenticated()
                .antMatchers(PHONE_CONFIRMATION).authenticated()
                .antMatchers(PHONE_CONFIRMATION + "/send").permitAll()
                .antMatchers(CREATE_POST).authenticated()
                .antMatchers(POST).permitAll()
                .antMatchers(EDIT_POST).authenticated();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
