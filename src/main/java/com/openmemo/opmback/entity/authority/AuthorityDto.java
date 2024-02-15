package com.openmemo.opmback.entity.authority;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDto {
    @JsonProperty("customer_id")
    private Integer customerId;
    @JsonProperty("rolename")
    private String rolename;
}
