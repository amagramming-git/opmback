package com.openmemo.opmback.config;

import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import com.openmemo.opmback.filter.JwtTokenGeneratorFilter;
import com.openmemo.opmback.filter.JwtTokenValidatorFilter;

@Configuration
public class ProjectSecurityConfig {
    @Value("${allowed.origin.opmfront}")
    private String ALLOWED_ORIGIN_OPMFRONT;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer
                        .configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(
                                    HttpServletRequest request) {
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(
                                        Collections.singletonList(ALLOWED_ORIGIN_OPMFRONT));
                                config.setAllowedMethods(
                                        Collections.singletonList(CorsConfiguration.ALL));
                                config.setAllowCredentials(true);
                                config.setAllowedHeaders(
                                        Collections.singletonList(CorsConfiguration.ALL));
                                config.setExposedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION));
                                config.setMaxAge(3600L);
                                return config;
                            }
                        }))
                .csrf((csrf) -> csrf.disable()) // 今後の課題だが、現時点では実装しない
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/admintest")
                        .hasRole("ADMIN") // ADMIN
                        .requestMatchers("/anytest").hasAnyRole("USER", "ADMIN")// ADMINもしくはUSER
                        .requestMatchers("/usertest", "/userposttest", "/api/post/**")
                        .hasRole("USER") // USER
                        .requestMatchers("/authtest", "/api/customer/get", "/api/customer/me")
                        .authenticated() // 認証の必要なリクエスト
                        .requestMatchers("/normaltest", "/api/customer/register").permitAll()) // 認証の必要無いリクエスト
                .anonymous((anonymous) -> anonymous.disable()).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
