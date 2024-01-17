package com.openmemo.opmback.entity.customer.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerGetResponceBody {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
}
