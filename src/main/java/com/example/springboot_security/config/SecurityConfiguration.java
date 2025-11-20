package com.example.springboot_security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final  DataSource dataSource;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request->request.requestMatchers("/h2-console/**")
                .permitAll()
                .anyRequest().authenticated());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());
        http.headers(header->header.frameOptions(f->f.sameOrigin()));
        return  (SecurityFilterChain) http.build();

    }
    @Bean
    UserDetailsManager userDetailsManager(){
        UserDetails user1= User.withUsername("aswin")
                .password("{noop}2465")
                .roles("ADMIN")
                .build();
        UserDetails user2= User.withUsername("abhi")
                .password("{noop}2465")
                .roles("USER")
                .build();
        JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);
        return userDetailsManager;
        //return new InMemoryUserDetailsManager(user1,user2);
    }
}
