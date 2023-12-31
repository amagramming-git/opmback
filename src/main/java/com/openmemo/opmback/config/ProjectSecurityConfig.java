package com.openmemo.opmback.config;

import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.openmemo.opmback.filter.CsrfCookieFilter;
import java.util.Arrays;
import com.openmemo.opmback.filter.JwtTokenGeneratorFilter;
import com.openmemo.opmback.filter.JwtTokenValidatorFilter;
import java.util.function.Consumer;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        
        Consumer<ResponseCookie.ResponseCookieBuilder> cookieCustomizer = (cookie) -> {
            // cookie.secure(true);
            cookie.secure(true);
            cookie.httpOnly(false);
            cookie.path("/");
            cookie.sameSite("None");
        };
        CookieCsrfTokenRepository cookieCsrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        cookieCsrfTokenRepository.setCookieCustomizer(cookieCustomizer);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // CORSアクセスする元のドメイン名
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                // .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
                //         .ignoringRequestMatchers("/customer/register","/customer/me") // csrfの例外を設定
                //         .csrfTokenRepository(cookieCsrfTokenRepository))
                // .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .csrf((csrf) -> csrf.disable())
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admintest").hasRole("ADMIN")
                        .requestMatchers("/anytest").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/usertest","/userposttest", "/post/**").hasRole("USER")
                        .requestMatchers("/authtest","/customer/get", "/customer/me").authenticated() // 認証の必要なリクエスト
                        .requestMatchers("/normaltest","/customer/register").permitAll()) // 認証の必要無いリクエスト
                // .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
