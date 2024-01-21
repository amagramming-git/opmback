package com.openmemo.opmback.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.openmemo.opmback.entity.authority.AuthorityDto;
import com.openmemo.opmback.entity.customer.CustomerDto;
import com.openmemo.opmback.service.AuthorityService;
import com.openmemo.opmback.service.CustomerService;

@Component
public class ProjectAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<CustomerDto> customerList = customerService.findByEmail(email);
        if (customerList.size() != 1) {
            throw new BadCredentialsException("No user registered with this details!");
        } else {
            if (passwordEncoder.matches(pwd, customerList.get(0).getPassword())) {
                List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(
                        authorityService.selectCustomerId(customerList.get(0).getId()));
                return new UsernamePasswordAuthenticationToken(email, pwd, grantedAuthorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<AuthorityDto> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (AuthorityDto authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRolename()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
