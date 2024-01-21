package com.openmemo.opmback.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openmemo.opmback.entity.authority.AuthorityDto;
import com.openmemo.opmback.mapper.authority.Authority;
import com.openmemo.opmback.mapper.authority.AuthorityExample;
import com.openmemo.opmback.mapper.authority.AuthorityMapper;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    public int insertRoleUser(int customerId) {
        Authority authority = new Authority();
        authority.setCustomerId(customerId);
        authority.setRolename("ROLE_USER");
        return authorityMapper.insertCustom(authority);
    }

    public List<AuthorityDto> selectCustomerId(int customerId) {
        AuthorityExample authorityExample = new AuthorityExample();
        authorityExample.createCriteria().andCustomerIdEqualTo(customerId);
        List<Authority> postList = authorityMapper.selectByExample(authorityExample);
        List<AuthorityDto> dtoList = setAuthorityDtoList(postList);
        return dtoList;
    }

    private List<AuthorityDto> setAuthorityDtoList(List<Authority> authorityList) {
        List<AuthorityDto> dtoList = new ArrayList<AuthorityDto>();
        for (Authority authority : authorityList) {
            AuthorityDto dto = setAuthorityDto(authority);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private AuthorityDto setAuthorityDto(Authority athority) {
        AuthorityDto dto = new AuthorityDto();
        dto.setId(athority.getId());
        dto.setCustomerId(athority.getCustomerId());
        dto.setRolename(athority.getRolename());
        return dto;
    }
}
