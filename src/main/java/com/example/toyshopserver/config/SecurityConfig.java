package com.example.toyshopserver.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigurationSource())
        .and()
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
            .requestMatchers("/api/staff/**").hasAnyRole("ADMIN", "STAFF")
            .requestMatchers("/api/cart/**").hasAnyRole("ADMIN", "STAFF", "USER")
            .requestMatchers("/api/order/**").hasAnyRole("ADMIN", "STAFF", "USER")
            .anyRequest().permitAll())
        .httpBasic();

    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers("/api/auth/**", "/api/products/**");
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(List.of("*"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
