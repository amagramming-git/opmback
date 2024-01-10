package com.openmemo.opmback.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openmemo.opmback.util.ConstantSecurity;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null != authorizationHeader){
            String authorizationType = authorizationHeader.split(" ")[0];
            String token = authorizationHeader.split(" ")[1];
            if (authorizationType.equals("Bearer") && (null != token)) {
                try {
                    SecretKey key = Keys.hmacShaKeyFor(
                            ConstantSecurity.JWT_KEY.getBytes(StandardCharsets.UTF_8));
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = (String) claims.get("authorities");
                    Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception e) {
                    throw new BadCredentialsException("Invalid Token received!");
                }
            }else{
                throw new BadCredentialsException("Not Bearer Token!");
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/customer/get");
    }

}
