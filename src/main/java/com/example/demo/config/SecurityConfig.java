package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ClientRegistrationRepository repository;

    private final AuthenticationSuccessHandler successHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            auth -> auth.requestMatchers("/css/**", "/webjars/**").permitAll()
                        .requestMatchers("/account/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
        ).oauth2Login(
            oauth -> oauth.successHandler(successHandler)
        ).logout(
            logout -> logout.invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutSuccessHandler(logoutSuccessHandler())
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        ).exceptionHandling(
            e -> e.accessDeniedPage("/error/403")
        );

        return http.build();
    }

    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler handler = new OidcClientInitiatedLogoutSuccessHandler(repository);
        //handler.setPostLogoutRedirectUri("{baseUrl}");
        
        return handler;
    }
    
}
