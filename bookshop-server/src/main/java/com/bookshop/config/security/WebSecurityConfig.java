package com.bookshop.config.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
//        securedEnabled = true,
//        jsr250Enabled = true,
        prePostEnabled = true)
@Order(1000)
@AllArgsConstructor
public class WebSecurityConfig {

    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public AuthTokenFilter authenticationJwtAuthTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection
            .cors(cors -> cors.disable())  // Disable CORS if not needed
            .formLogin(form -> form.disable())  // Disable form login
            .httpBasic(basic -> basic.disable())  // Disable HTTP Basic authentication
            .logout(logout -> logout.disable());  // Disable logout functionality

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
