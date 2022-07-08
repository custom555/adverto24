package com.custom555.adverto24.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/img/**","/styles/**").permitAll()
                .mvcMatchers("/register/**").permitAll()
                .mvcMatchers("/my-panel/**").hasAnyRole("USER","ADMIN")
                .mvcMatchers("/h2-console/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        http.formLogin(login -> login.loginPage("/login").permitAll());
        http.logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll());
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
