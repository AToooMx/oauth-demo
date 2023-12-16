package com.shashok.oauth.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("rashashok")
                .password("$2a$12$546uUPgZZcJSiBXErbkc/e2H4nxXCvztRaf6Dsc4VwgPV8qEDcL5W")
                .roles("USER").build();

        UserDetails admin = User
                .withUsername("admin")
                .password("$2a$12$oUpr0KVu726d.tQpbTuUUOdvYYUWDBXVP1/qvPgiKpjX/lPtaZFtW")
                .roles("USER", "ADMIN")
                .build();
        return  new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .userDetailsService(userDetailsService())
                .formLogin(config -> config.defaultSuccessUrl("/memory/user"))
                .oauth2Login(config -> config.defaultSuccessUrl("/oauth/user"))
                .authorizeHttpRequests(c-> c
                        .requestMatchers("/unsecured/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
