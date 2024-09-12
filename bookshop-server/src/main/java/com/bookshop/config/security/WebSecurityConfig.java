package com.bookshop.config.security;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1000)
@AllArgsConstructor
public class WebSecurityConfig {

        private UserDetailsServiceImpl userDetailsService;

//        @Qualifier("handlerExceptionResolver")
//        private HandlerExceptionResolver resolver;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

//        @Bean
//        public AccessDeniedHandler accessDeniedHandler() {
//            return (request, response, exception) -> resolver.resolveException(request, response, null, exception);
//        }

//        @Bean
//        public AuthenticationEntryPoint authenticationEntryPoint() {
//            return (request, response, exception) -> resolver.resolveException(request, response, null, exception);
//        }

//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(authenticationProvider());
//        }

        @Bean
        public AuthTokenFilter authenticationJwAuthTokenFilter() {
            return new AuthTokenFilter();
        }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection
            .cors(cors -> cors.disable())  // Disable CORS if not needed
            .formLogin(form -> form.disable())  // Disable form login
            .httpBasic(basic -> basic.disable())  // Disable HTTP Basic authentication
            .logout(logout -> logout.disable());  // Disable logout functionality

         http.addFilterBefore(authenticationJwAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
